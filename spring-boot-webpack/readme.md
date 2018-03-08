### backend

- springboot : https://start.spring.io


### frontend

- vue-cli :  ` npm install -g vue-cli` 

  - `vue-cli`를 통해 webpack으로 `frontend` 생성

- `build/build.js` 경로를 `build_script`로 변경 : build는 `gradle` 통해서 output

  - 변경된 파일 목록

    ```
      ./eslintignore
      .eslintrc.js
      package.json
      runner.js
      karama.conf.js
    ```


### build.gradle

최종 빌드 후에 rootProject build 경로에 복사

```groovy
// ./backend/build.gradle
dependencies {
	compile project(':frontend') // frontend 프로젝트 dependencies
	compile('org.springframework.boot:spring-boot-starter-web')
	testCompile('org.springframework.boot:spring-boot-starter-test')
}

// 리소스 복사
processResources {
    from ('../frontend/dist/') {
        into 'static'
		// into 'public'
    }
}

task copyToLib(type: Copy) {
	println 'copyToLib'

	from 'build/libs' //대상
	into '../build/libs' //복사할 곳
	// rename { String fileName ->
	// 	fileName.replace("input", "output") //input-바꿀대상, output-원하는이름
	// }
}

build.finalizedBy(copyToLib)
```



### TODO

- war 파일처리
- API 연결

