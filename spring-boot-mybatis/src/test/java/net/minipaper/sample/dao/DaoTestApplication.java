/**
 * Copyright 2015-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.minipaper.sample.dao;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Spring Boot Application for testing {@link org.mybatis.spring.boot.test.autoconfigure.MybatisTest @MybatisTest}.
 * <p>
 * This class has role for prevent to run the {@link net.minipaper.sample.SpringBootMybatisSampleApplication}.
 * For more detail information, please refer <a href="http://stackoverflow.com/questions/42722480/jdbctest-detect-class-annotated-springbootapplication">Here</a>.
 *
 * @author Kazuki Shimizu
 * @since 1.2.1
 */
@SpringBootApplication
public class DaoTestApplication {
}
