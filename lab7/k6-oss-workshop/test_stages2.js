import http from 'k6/http';
import { check } from 'k6';

const BASE_URL = __ENV.BASE_URL || "http://localhost:3333";

export const options = {
  stages: [
    { duration: '30s', target: 20 },
    { duration: '30s', target: 20 },
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<1100'], // SLO de latência
    http_req_failed: ['rate<0.01'],    // SLO de falhas
    checks: ['rate>0.98'],             // Pelo menos 98% dos checks devem passar
  },
};

export default function () {
  let restrictions = {
    maxCaloriesPerSlice: 500,
    mustBeVegetarian: false,
    excludedIngredients: ["pepperoni"],
    excludedTools: ["knife"],
    maxNumberOfToppings: 6,
    minNumberOfToppings: 2,
  };

  let res = http.post(`${BASE_URL}/api/pizza`, JSON.stringify(restrictions), {
    headers: {
      "Content-Type": "application/json",
      "Authorization": "token abcdef0123456789",
      "X-User-ID": "23423", // deve ser string para header
    },
  });

  // Verificações (checks)
  const success = check(res, {
    'status é 200': (r) => r.status === 200,
    'body < 1KB':   (r) => r.body && r.body.length < 1024,
  });

  if (success && res.json().pizza) {
    console.log(`${res.json().pizza.name} (${res.json().pizza.ingredients.length} ingredients)`);
  }
}
