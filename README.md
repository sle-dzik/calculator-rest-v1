# calculator-rest-v1

Endpoint: GET /calculus?query=[input]

input should be base64 endoded string of math expression like: 2 * (23/(3*3))- 23 * (2*3)

Heroku endpiont:
https://calculator-rest-v1.herokuapp.com/calculus?query=MiAqICgyMy8oMyozKSktIDIzICogKDIqMyk


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


Deployment process:


1. Deploy to stage

![page1](https://user-images.githubusercontent.com/98649310/152417131-c6de1405-1211-47c7-922a-a1a01ff92b43.JPG)

2.Promote changes to prod

![image](https://user-images.githubusercontent.com/98649310/152418505-73509400-a85b-4d81-adf2-a577ed1a9d41.png)

![page2](https://user-images.githubusercontent.com/98649310/152417165-313a49a6-250a-4348-9daa-7a1881293ca1.jpg)
