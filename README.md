# org.openapitools.client - Kotlin client library for code-dict API

## Requires

* Kotlin 1.4.30
* Gradle 6.8.3

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountResourceApi* | [**accountResourceDetail**](docs/AccountResourceApi.md#accountresourcedetail) | **GET** /accounts/{id} | 
*AccountResourceApi* | [**accountResourceLogin**](docs/AccountResourceApi.md#accountresourcelogin) | **POST** /accounts/login | 
*AccountResourceApi* | [**accountResourceRegister**](docs/AccountResourceApi.md#accountresourceregister) | **POST** /accounts | 
*AdminResourceApi* | [**adminResourceAdminResource**](docs/AdminResourceApi.md#adminresourceadminresource) | **GET** /api/admin | 
*PublicResourceApi* | [**publicResourcePublicResource**](docs/PublicResourceApi.md#publicresourcepublicresource) | **GET** /api/public | 
*UserResourceApi* | [**userResourceMe**](docs/UserResourceApi.md#userresourceme) | **GET** /api/users/me | 
*WordBookApi* | [**wordBookResourceDeleteWordBook**](docs/WordBookApi.md#wordbookresourcedeletewordbook) | **DELETE** /word-books/delete/{wordBookId} | 
*WordBookApi* | [**wordBookResourceDetail**](docs/WordBookApi.md#wordbookresourcedetail) | **GET** /word-books/{id} | 
*WordBookApi* | [**wordBookResourceGetAllWordBooks**](docs/WordBookApi.md#wordbookresourcegetallwordbooks) | **GET** /word-books/all | 
*WordBookApi* | [**wordBookResourceNewWordBook**](docs/WordBookApi.md#wordbookresourcenewwordbook) | **POST** /word-books | 
*WordBookApi* | [**wordBookResourceRenameWordBook**](docs/WordBookApi.md#wordbookresourcerenamewordbook) | **PUT** /word-books/{wordBookId}/name/{name} | 
*WordBookItemApi* | [**wordBookItemResourceAppendToWordBook**](docs/WordBookItemApi.md#wordbookitemresourceappendtowordbook) | **POST** /word-book-items/append/{wordBookId}/{wordId} | 
*WordBookItemApi* | [**wordBookItemResourceRemoveFromWordBook**](docs/WordBookItemApi.md#wordbookitemresourceremovefromwordbook) | **DELETE** /word-book-items/{id} | 
*WordCommentResourceApi* | [**wordCommentResourceAppend**](docs/WordCommentResourceApi.md#wordcommentresourceappend) | **POST** /comments | 
*WordCommentResourceApi* | [**wordCommentResourceDelete**](docs/WordCommentResourceApi.md#wordcommentresourcedelete) | **DELETE** /comments/{id} | 
*WordCommentResourceApi* | [**wordCommentResourceDetail**](docs/WordCommentResourceApi.md#wordcommentresourcedetail) | **GET** /comments/{id} | 
*WordResourceApi* | [**wordResourceChatStreaming**](docs/WordResourceApi.md#wordresourcechatstreaming) | **GET** /words/chat/streaming | 
*WordResourceApi* | [**wordResourceDetail**](docs/WordResourceApi.md#wordresourcedetail) | **GET** /words/detail | 
*WordResourceApi* | [**wordResourceDetailById**](docs/WordResourceApi.md#wordresourcedetailbyid) | **GET** /words/{id} | 
*WordResourceApi* | [**wordResourceExplain**](docs/WordResourceApi.md#wordresourceexplain) | **GET** /words/ai/explain | 
*WordResourceApi* | [**wordResourceRandom**](docs/WordResourceApi.md#wordresourcerandom) | **GET** /words/random | 
*WordResourceApi* | [**wordResourceSearch**](docs/WordResourceApi.md#wordresourcesearch) | **GET** /words/search | 


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.Account](docs/Account.md)
 - [org.openapitools.client.models.AccountLoginReq](docs/AccountLoginReq.md)
 - [org.openapitools.client.models.WordBook](docs/WordBook.md)
 - [org.openapitools.client.models.WordBookItem](docs/WordBookItem.md)
 - [org.openapitools.client.models.WordComment](docs/WordComment.md)
 - [org.openapitools.client.models.WordDetailVo](docs/WordDetailVo.md)
 - [org.openapitools.client.models.WordSearchVo](docs/WordSearchVo.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

<a name="SecurityScheme"></a>
### SecurityScheme

- **Type**: HTTP basic authentication

