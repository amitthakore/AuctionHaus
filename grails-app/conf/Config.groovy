// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
grails.debug.productionOverride=anything
// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

grails.gorm.failOnError = true

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true
grails.views.javascript.library="jquery"
// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
    debug 'org.springframework.security',
      'grails.app.jobs'
    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:

grails.plugins.springsecurity.userLookup.userDomainClassName = 'auctionhaus.Customer'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'auctionhaus.CustomerRole'
grails.plugins.springsecurity.userLookup.usernamePropertyName = 'username'
//grails.plugins.springsecurity.userLookup.authoritiesPropertyName = 'authorities'
grails.plugins.springsecurity.authority.className = 'auctionhaus.Role'
//grails.plugins.springsecurity.password.algorithm='SHA-512'
grails.plugins.springsecurity.securityConfigType = "Annotation"
//grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.routing.jms.brokerURL = "tcp://localhost:61616"
//grails.plugins.springsecurity.interceptUrlMap = [
  //      '/secure/**':    ['ROLE_ADMIN'],
    //    '/finance/**':   ['ROLE_FINANCE', 'IS_AUTHENTICATED_FULLY'],
      //  '/js/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
      //  '/css/**':       ['IS_AUTHENTICATED_ANONYMOUSLY'],
      // '/images/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
      //  '/*':            ['IS_AUTHENTICATED_ANONYMOUSLY'],
     //   '/login/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
     //   '/logout/**':    ['IS_AUTHENTICATED_ANONYMOUSLY']
//]

grails {
    mail {
        host = "smtp.gmail.com"
        port = 587
        username = "amitthakore16@gmail.com"
        password = "aditi1972"
        props = ["mail.debug": "true",
                "mail.smtp.protocol": "smtps",
                "mail.smtp.auth": "true",
                "mail.smtp.starttls.enable": "true",
                "mail.smtp.host": "smtp.gmail.com",
                "mail.smtp.user": "xyz@gmail.com",
                "mail.smtp.password": "mypassword"]
    }
}

grails.plugins.springsecurity.auth.loginFormUrl = '/'
grails.plugins.springsecurity.failureHandler.defaultFailureUrl = '/'
