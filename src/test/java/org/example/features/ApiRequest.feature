Feature: ApiRequest

    Scenario: Midas api request scenario
      * Create new rest api request.
      * "https://api.spacex.land/rest" base url add for the rest api.
      * "/capsule/{id}" base path add for the rest api.
      * Add path param with 'id' key and 'C102' value.
      * Add header with 'accept' key and 'application/json' value
      * Send GET for rest api request.
      * Print Response body.
      * Save rest api response 'dragon.id' json key value save to 'dragonIdVariable' variable.
      * Check response status code is '200'.

      * Create new rest api request.
      * "https://api.spacex.land/rest" base url add for the rest api.
      * "/dragon/{id}" base path add for the rest api.
      * Add path param with 'id' key and 'dragonIdVariable' saved variable value.
      * Add header with 'accept' key and 'application/json' value
      * Send GET for rest api request.
      * Print Response body.
      * Check response status code is '200'.
      * Response json 'heat_shield.dev_partner' value is equal to "NASA" control .
      * Response json 'heat_shield.material' value is equal to "PICA-X" control .
      * Response json 'heat_shield.size_meters' value is equal to "3.6" control .
      * Response json 'heat_shield.temp_degrees' value is equal to "3000" control .