# serverless-scrapping API - Ariel Mirra

## How to use
This api implements the following OAS Spec: 

    {
      "openapi": "3.0.0",
      "info": {
        "title": "Serverless API - Ariel Mirra",
        "description": "API to make requests to the serverless service built for TP7 DPOI",
        "version": "1.0.0"
      },
      "servers": [
        {
          "url": "https://0tihe25hqk.execute-api.us-east-1.amazonaws.com/test"
        }
      ],
      "paths": {
        "/scraps": {
          "get": {
            "summary": "Returns a list of saved scraps",
            "responses": {
              "200": {
                "description": "A JSON array of scraps",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              }
            }
          },
          "post": {
            "summary": "Makes a scrap",
            "responses": {
              "201": {
                "description": "Scrap saved successfully",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "string"
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

### Test it
In postman:
- make a GET request to https://0tihe25hqk.execute-api.us-east-1.amazonaws.com/test/scraps to get all scraps saved
- make a POST request to https://0tihe25hqk.execute-api.us-east-1.amazonaws.com/test to scrap an url. The url must be given in the request body like so: 

    {
        "url": "http://www.lanacion.com.ar/2351917"
    }

That's all!
