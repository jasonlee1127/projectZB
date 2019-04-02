package com.zerob.configuration.common;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 2019-04-02 - jasonLee
 * servlet-context.xml 의 java config 버전
 * EnableWebMvc: xml의 <annotation-driven /> 와 동일한 기능
 * 다시한번 확인 : https://wedul.site/349
 * @author jason
 */
@Configuration
@EnableWebMvc
//@ComponentScan(
//		basePackages = "com.zerob",
//		includeFilters = {
//				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Controller.class)
//		},
//		excludeFilters = {
//				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Service.class),
//				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Repository.class),
//		}
//)
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

	/**
	 * 2019-04-02 - jasonLee
	 * spring resource 지정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/**
		 * xml의 <resources mapping="/resources/**" location="/resources/" />과 같은 방식이다.
		 */
		registry.addResourceHandler("/resource/**").addResourceLocations("/resource/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		/**
		 * <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        		<beans:property name="prefix" value="/WEB-INF/views/" />
				<beans:property name="suffix" value=".jsp" />
				<beans:property name="order"  value="2" />
			</beans:bean>
		 */
		registry.jsp("/WEB-INF/view/", ".jsp");
		registry.order(2);
	}
	
}
