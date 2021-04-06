### Logging dependency principles:

- Do not use logging framework directly,
  but use in your code logging bridge interfaces
  (`slf4j`, `log4j2`).
  Do not use JCL - as a logging bridge. It is a legacy.
- For standalone applications add logging dependency via maven.
- For managed applications (for instance deployed to the Wildfly), 
  configure actual logging dependency via the server configuration.
  Do not include maven dependencies on a real logging implementations.


### Logging frameworks management for java applications, deployed to WildFly

- [Slf4j for WildFly (preferred)](slf4j_wildfly/README.md)
- [Log4j2 for WildFly](log4j2_wildfly/README.md)

### Logging frameworks for standalone java applications

- [Log4j2 with mix of other logging frameworks](log4j2_mix/README.md)
- [Slf4j with mix of other logging frameworks](slf4j_mix/README.md)
- [Log4j2](log4j2/README.md)
- [Slf4j with logback](slf4j_logback/README.md)
- [Slf4j with log4j2](slf4j_log4j2/README.md)
  
##### Legacy solutions, do not use them now:
- [Slf4j with log4j](slf4j_log4j2/README.md)
- [JCL with log4j](jcl_log4j/README.md)
- [Log4j](log4j/README.md)
- [JUL](jul/README.md)


