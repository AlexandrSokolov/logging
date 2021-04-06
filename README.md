### Logging dependency principles:

- Do not use logging framework directly, 
  in your code use logging bridge interfaces in your code 
  (slf4j, log4j2).
  Do not use JCL - as a logging bridge. It is a legacy solution.
- For standalone applications configure add logging dependency via maven.
- For managed applications (for instance deployed to the Wildfly), 
  configure actual logging dependency via the server configuration.

### Logging frameworks

- [Log4j2 (recommended)](log4j2/README.md)
- [Slf4j with logback](slf4j_logback/README.md)
- [Slf4j with log4j2](slf4j_log4j2/README.md)
- [JCL with log4j (legacy)](jcl_log4j/README.md)
- [Log4j (legacy)](log4j/README.md)
- [JUL (legacy)](jul/README.md)


