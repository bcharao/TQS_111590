7.2
-a)

• How long did the API calls take?
Average: 290.45 ms

Minimum: 13.86 ms

Maximum: 1.03 s

• How many requests were made?
http_reqs: 1,050

• How many requests failed?
http_req_failed: 0.00%

b) Metrics 20VUs over 30sec

    checks
    ✓ 'rate>0.99' rate=100.00%

    http_req_duration
    ✓ 'p(95)<1100' p(95)=547.46ms

    http_req_failed
    ✓ 'rate<0.01' rate=0.00%



    checks_total.......................: 9412    104.469157/s
    checks_succeeded...................: 100.00% 9412 out of 9412
    checks_failed......................: 0.00%   0 out of 9412

    ✓ status is 200
    ✓ body size is < 1KB

    HTTP
    http_req_duration.......................................................: avg=258.4ms  min=4.99ms med=236.58ms max=1.11s p(90)=473.68ms p(95)=547.46ms
    { expected_response:true }............................................: avg=258.4ms  min=4.99ms med=236.58ms max=1.11s p(90)=473.68ms p(95)=547.46ms
    http_req_failed.........................................................: 0.00%  0 out of 4706
    http_reqs...............................................................: 4706   52.234578/s

    EXECUTION
    iteration_duration......................................................: avg=258.75ms min=5.26ms med=236.91ms max=1.11s p(90)=473.93ms p(95)=547.85ms
    iterations..............................................................: 4706   52.234578/s
    vus.....................................................................: 1      min=1         max=20
    vus_max.................................................................: 20     min=20        max=20

    NETWORK
    data_received...........................................................: 3.4 MB 38 kB/s
    data_sent...............................................................: 1.7 MB 19 kB/s

f) Metrics 120+VUs over 30sec

    checks
    ✗ 'rate>0.99' rate=76.33%

    http_req_duration
    ✗ 'p(95)<1100' p(95)=2.49s

    http_req_failed
    ✗ 'rate<0.01' rate=47.33%

    checks_total.......................: 9958   110.61814/s
    checks_succeeded...................: 76.33% 7601 out of 9958
    checks_failed......................: 23.66% 2357 out of 9958

    ✗ status is 200
    ↳  52% — ✓ 2622 / ✗ 2357
    ✓ body size is < 1KB

    HTTP
    http_req_duration.......................................................: avg=1.46s min=31.05ms med=1.46s max=4.02s p(90)=2.21s p(95)=2.49s
    { expected_response:true }............................................: avg=1.15s min=31.05ms med=1.11s max=3.57s p(90)=1.88s p(95)=2.15s
    http_req_failed.........................................................: 47.33% 2357 out of 4979
    http_reqs...............................................................: 4979   55.30907/s

    EXECUTION
    iteration_duration......................................................: avg=1.46s min=31.38ms med=1.46s max=4.02s p(90)=2.21s p(95)=2.49s
    iterations..............................................................: 4979   55.30907/s
    vus.....................................................................: 1      min=1            max=120
    vus_max.................................................................: 120    min=120          max=120

    NETWORK
    data_received...........................................................: 2.2 MB 24 kB/s
    data_sent...............................................................: 1.8 MB 20 kB/s