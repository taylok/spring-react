# Spring React
## Build out this example
1. The base react router has patterns to force all responses not on /api to the index file. 
2. Ignore Spring Security when running on kubernetes , allow a proxy that sits in front to handle authentication. Use JWTs to make sure scopes and roles.
3. How would the example look different with graphQL? You would just need to change the fetch command.
## Next
* Add additional pages or modals for detailed views
* Add forms mto modify and add data
* Add delete functions and dynamically reload the pages
* Style the app using Bootstrap or MUI
## Security
* Security with React and Spring Boot is not an easy topic
* More common to use eternal authentication where possible
* More modern approach: avoid the topic by offloading it to a reverse proxy that fronts the application with a JWT-based provider
