# wanted-pre-onboarding-backend
## 💙소개 
> 회사의 채용공고를 등록, 수정, 삭제, 조회 하고, 유저는 채용공고에 지원을 하는 API 서비스.




## ER-Diagram

![ER-다이어그램](https://github.com/hyunkkkk/wanted-pre-onboarding-backend/blob/main/wanted.drawio.png)




## 📃 API 명세서


### 회사

> 회사 등록을 위해 사용됩니다.

#### 회사 등록

`POST` 요청을 사용해서 새 회사를 등록할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|
| `name`         | `String` | 회사 이름    |
| `country` | `String` | 본사 위치 국가   |
| `location` | `String` | 회사 위치     |

##### Example request

``` http request
POST http://localhost:8088/api/companies
Content-Type: application/json

{
  "name": "Toss",
  "country": "korea",
  "location": "suwon"
}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `id`        | `Long`          | 컴패니 ID        |
| `name`         | `String` | 회사 이름    |
| `country` | `String` | 본사 위치 국가   |
| `location` | `String` | 회사 위치     |
| `recruitments` | `List<Recruitment>` | 회사의 채용공고 리스트     |

##### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "id": 1,
    "name": "Toss",
    "country": "korea",
    "location": "suwon",
    "recruitments": null
}
```




---
### 채용공고

> 채용공고 등록, 수정, 삭제, 조회를 위해 사용됩니다.

#### 채용공고 등록

`POST` 요청을 사용해서 채용공고를 등록할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|
| `companyId`         | `Long` | 회사_id    |
| `position` | `String` | 채용 직무   |
| `reward` | `Int` | 보상금     |
| `description` | `String` | 상세설명   |
| `tech` | `String` | 사용 기술     |

##### Example request

``` http request
POST http://localhost:8088/api/recruitments
Content-Type: application/json

{
  "companyId":1,
  "position":"백 주니어 개발자",
  "reward":99,
  "description":"원티드랩",
  "tech":"Python"
}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `status`        | `Int`          | 상태코드        |
| `id`         | `Long` | 채용공고_id    |
| `company` | `String` | 회사 이름   |
| `country` | `String` | 본사 위치 국가   |
| `location` | `String` | 회사 위치     |
| `position` | `String` | 직무     |
| `reward` | `Int` | 보상금     |
| `tech` | `String` | 사용 기술     |
| `httpStatus` | `String` | 상태코드     |


##### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "status": 200,
    "id": 3,
    "company": "카카오",
    "country": "korea",
    "location": "seoul",
    "position": "백 주니어 개발자",
    "reward": 99,
    "tech": "Python",
    "httpStatus": "OK"
}
```

---


#### 채용공고 수정

`PUT` 요청을 사용해서 채용공고를 수정할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|
| `position` | `String` | 채용 직무   |
| `reward` |  `Int`  | 보상금     |
| `description` | `String` | 상세설명   |
| `tech` | `String` | 사용 기술     |

##### Example request

``` http request
PUT http://localhost:8088/api/recruitments/2(채용공고_id)
Content-Type: application/json

{
  "position":"백 시니어 개발자",
  "reward":16,
  "description":"원티드랩",
  "tech":"Python"
}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `status`        | `Int`          | 상태코드        |
| `id`         | `Long` | 채용공고_id    |
| `company` | `String` | 회사 이름   |
| `country` | `String` | 본사 위치 국가   |
| `location` | `String` | 회사 위치     |
| `position` | `String` | 직무     |
| `reward` | `Int` | 보상금     |
| `tech` | `String` | 사용 기술     |
| `httpStatus` | `String` | 상태코드     |


##### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "status": 200,
    "id": 3,
    "company": "카카오",
    "country": "korea",
    "location": "seoul",
    "position": "백 시니어 개발자",
    "reward": 16,
    "tech": "Python",
    "httpStatus": "OK"
}
```



---

#### 채용공고 삭제

`DELETE` 요청을 사용해서 채용공고를 수정할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|


##### Example request

``` http request
DELETE http://localhost:8088/api/recruitments/2(채용공고_id)
Content-Type: application/json

{

}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|



##### Example response

``` http request
HTTP/1.1 204 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{

}
```

---


#### 모든 채용공고 조회

`GET` 요청을 사용해서 모든 채용공고를 조회할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|


##### Example request

``` http request
GET http://localhost:8088/api/recruitments
Content-Type: application/json

{

}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `status`        | `Int`          | 상태코드        |
| `id`         | `Long` | 채용공고_id    |
| `company` | `String` | 회사 이름   |
| `country` | `String` | 본사 위치 국가   |
| `location` | `String` | 회사 위치     |
| `position` | `String` | 직무     |
| `reward` | `Int` | 보상금     |
| `tech` | `String` | 사용 기술     |
| `description` | `String` | 상세설명     |
| `otherRecruitmentIds` | `List<Long>` | 회사의 다른 채용공고_id     |
| `httpStatus` | `String` | 상태코드     |


##### Example response

``` http request
HTTP/1.1 204 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  {
      "status": 200,
      "id": 2,
      "company": "카카오",
      "country": "korea",
      "location": "seoul",
      "position": "백 주니어 개발자",
      "reward": 99,
      "tech": "Python",
      "httpStatus": "OK"
  },
  {
      "status": 200,
      "id": 2,
      "company": "Toss",
      "country": "korea",
      "location": "seoul",
      "position": "백 시니어 개발자",
      "reward": 16,
      "tech": "Python",
      "httpStatus": "OK"
  }
}
```


---

#### 채용공고 상세 조회

`GET` 요청을 사용해서 채용공고를 조회할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|


##### Example request

``` http request
GET http://localhost:8088/api/recruitments/2(채용공고_id)
Content-Type: application/json

{

}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `status`        | `Int`          | 상태코드        |
| `id`         | `Long` | 채용공고_id    |
| `company` | `String` | 회사 이름   |
| `country` | `String` | 본사 위치 국가   |
| `location` |  `String` | 회사 위치     |
| `position` | `String` | 직무     |
| `reward` | `Int` | 보상금     |
| `tech` | `String` | 사용 기술     |
| `description` | `String` | 상세설명     |
| `otherRecruitmentIds` | `List<Long>` | 회사의 다른 채용공고_id     |
| `httpStatus` | `String` | 상태코드     |


##### Example response

``` http request
HTTP/1.1 204 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "status": 200,
    "id": 2,
    "company": "카카오",
    "country": "korea",
    "location": "seoul",
    "position": "백 주니어 개발자",
    "reward": 99,
    "tech": "Python",
    "description": "원티드랩",
    "otherRecruitmentIds": [
        1,
        3
    ],
    "httpStatus": "OK"
}
```


---


#### 채용공고 검색

`GET` 요청을 사용해서 채용공고를 조회할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|


##### Example request

``` http request
GET http://localhost:8088/api/recruitments/search?search="원티드"
Content-Type: application/json

{

}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `status`        | `Int`          | 상태코드        |
| `id`         | `Long` | 채용공고_id    |
| `company` | `String` | 회사 이름   |
| `country` | `String` | 본사 위치 국가   |
| `location` | `String` | 회사 위치     |
| `position` | `String` | 직무     |
| `reward` | `Int` | 보상금     |
| `tech` | `String` | 사용 기술     |
| `httpStatus` | `String` | 상태코드     |


##### Example response

``` http request
HTTP/1.1 204 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "status": 200,
    "id": 2,
    "company": "카카오",
    "country": "korea",
    "location": "seoul",
    "position": "백 주니어 개발자",
    "reward": 99,
    "tech": "Python",
    "httpStatus": "OK"
}
```


---

### 유저

#### 유저 등록

`POST` 요청을 사용해서 유저를 등록할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|
| `name` | `String` | 유저 이름   |
| `email` | `String` | 유저 이메일     |


##### Example request

``` http request
POST http://localhost:8088/api/users
Content-Type: application/json

{
    "name" : "kk",
    "email" : "769g@g.com"
}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `id`         | `Long` | 유저_id    |
| `name` | `String` | 유저 이름   |
| `email` | `String` | 유저 이메일   |



##### Example response

``` http request
HTTP/1.1 204 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "id": 2,
    "name": "kk",
    "email": "769g@g.com"
}
```


---

### 지원서

#### 지원서 등록

`POST` 요청을 사용해서 유저를 등록할 수 있습니다.

##### Request fields

| Path             | Type   | Description |
|------------------|--------|-------------|



##### Example request

``` http request
POST http://localhost:8088/api/users/apply?recruitmentId=1&userId=1
Content-Type: application/json

{

}
```

##### Response fields

| Path            | Type            | Description |
|-----------------|-----------------|-------------|
| `status`         | `Int` | 상태코드    |
| `id` | `Long` | 지원서_id   |
| `recruitment_id` | `Long` | 채용공고_id   |
| `user_id` | `Long` | 유저_id   |
| `httpStatus` | `String` | 상태코드   |



##### Example response

``` http request
HTTP/1.1 204 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "status": 200,
    "id": 2,
    "recruitment_id": 1,
    "user_id": 2,
    "httpStatus": "OK"
}
```
