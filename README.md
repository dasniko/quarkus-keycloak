# Quarkus & Keycloak

## Securing your service with proper authentication

This is the corresponding repository to [my YouTube videos](https://www.youtube.com/c/NikoKöbler?sub_confirmation=1) about securing a [Quarkus](https://quarkus.io) service/app with proper authentication with the help of [Keycloak](https://www.keycloak.org).

### Most basic configuration/implementation with Bearer-only token for REST endpoints

* `UsersResource` - endpoints secured for authenticated users with the `user` role.
* `AdminResource` - endpoint secured only for authenticated users with the `admin` role.
* Unit/integration tests for all the endpoints (running Keycloak server in environment necessary).

[![](http://img.youtube.com/vi/F_VbzqqqRq8/maxresdefault.jpg)](http://www.youtube.com/watch?v=F_VbzqqqRq8 "")
