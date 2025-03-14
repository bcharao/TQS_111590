    4.3 - b)

    - Are there any instances of “xpath”? 
    Não. No código, todos os seletores utilizam By.cssSelector() em vez de By.xpath(),
    garantindo melhor desempenho e estabilidade.

    - What about identifier-based locators? 
    Sim, o código atual utiliza atributos e classes CSS, como data-testid e class, para localizar os elementos.
    Estes são mais estáveis e rápidos do que XPath.

    -  Which locator strategies are more robust?
    A abordagem atual, que utiliza CSS Selectors baseados em atributos e classes, é mais robusta do que XPath.
    Se os elementos tivessem um id único, essa seria ainda melhor opção.