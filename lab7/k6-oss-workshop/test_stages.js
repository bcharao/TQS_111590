import http from 'k6/http';
import { check } from 'k6';

const BASE_URL = __ENV.BASE_URL || "http://localhost:3333";
// Configuração dos estágios de carga
export const options = {
  stages: [
    // Sobe de 0 para 20 usuários virtuais em 5 segundos
    { duration: '5s', target: 20 },

    // Mantém 20 usuários por 10 segundos
    { duration: '10s', target: 20 },

    // Desce de 20 para 0 usuários em 5 segundos
    { duration: '5s', target: 0 },
  ],
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
      "X-User-ID": 23423,
    },
  });
  console.log(`${res.json().pizza.name} (${res.json().pizza.ingredients.length} ingredients)`);
}
