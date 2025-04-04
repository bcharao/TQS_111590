import http from "k6/http";
import { check } from "k6";

export const options = {
    stages: [
        { duration: '30s', target: 120 },
        { duration: '30s', target: 120 },
        { duration: '30s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ["p(95)<1100"], // 95% of requests must complete below 1.1s
        http_req_failed: ["rate<0.01"], // failures must be less than 1%
        checks: ["rate>0.99"], // at least 99% of checks must pass
    },
};

const BASE_URL = __ENV.BASE_URL || "http://localhost:3333";

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
            "X-User-ID": 23423,
            "Authorization": "token abcdef0123456789",
        },
    });

    check(res, {
        "status is 200": (r) => r.status === 200,
        "body size is < 1KB": (r) => r.body.length < 1024,
    });

    console.log(`${res.json().pizza.name} (${res.json().pizza.ingredients.length} ingredients)`);
}