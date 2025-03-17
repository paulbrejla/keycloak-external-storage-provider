# Keycloak External User Storage

- This POC implements a custom User Storage SPI to fetch user data from an external service and issues an access token for that user. 

```
Token header
------------
{
  "typ": "JWT",
  "alg": "RS256",
  "kid": "0lZUUv6Xo3_7_rwFaoySVQaYZ4ey6Ki_o9UA6ZWXzCU"
}

Token claims
------------
{
  "acr": "1",
  "auth_time": 1742197453,
  "azp": "account",
  "email_verified": false,
  "exp": 1742197772,
  "iat": 1742197472,
  "iss": "http://localhost:8082/realms/pos",
  "jti": "1454d587-6b7d-41ca-88fc-858c791ab8af",
  "preferred_username": "paulbrejla",
  "resource_access": {
    "account": {
      "roles": [
        "manage-account",
        "manage-account-links",
        "view-profile"
      ]
    }
  },
  "scope": "profile email",
  "sid": "c0aad104-a5b1-4ed3-8539-5a69840a3422",
  "sub": "f:b68757d1-f32c-405d-91b5-0176375e1afa:paulbrejla",
  "typ": "Bearer"
}
```
