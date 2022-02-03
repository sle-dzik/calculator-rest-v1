# calculator-rest-v1

Calculator assumptions: 
1. Consume only positive numbers on input, decimal numbers are not allowed
2. It removes all spaces between numbers. If you provide: [2 2+ 2] calculator understands it as: [22+2]
3.  Supported operations: + - * / ( )
4. If you provide wrong base64 you will recieve:

``{
    "error": true,
    "message": "Unable to decode base64 value:[MiAqICgyMy8oMy.ozKSktIDIzICogKDIqMyk]"
}``

5. If you don't provide query base64 you will recieve HttpStatus 400 Bad Request:

``{
    "error": true,
    "message": "Request query cannot be empty"
}``

6. If you divide by 0 you will revieve HttpStatus 400 Bad Request:

``{
    "error": true,
    "message": "Divide by 0 is prohibited"
}``

7. If you provide wrong expression like: 2**2 you will revieve HttpStatus 400 Bad Request:

``
{
    "error": true,
    "message": "Invalid expression"
}
``

8. If you provide wrong expression like: 2*s2 you will revieve HttpStatus 400 Bad Request:

``
{
    "error": true,
    "message": "Not supported expression element: s"
}
``


Technical:
- to run only ITs plase use command:
``mvn failsafe:integration-test``

- to run UTs please use command:
``mvn test``

- to run all tests
``mvn clean verify``
