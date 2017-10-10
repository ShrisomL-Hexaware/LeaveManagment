# git

## repository actions

### clone
  * ```git clone git@github.com:HexaInnovLab/ftpxx.git```

### reset

## checkout/branch

## stash

## file actions

  * Checkout a previous version
    * `git checkout <commit> <filename>`
  * Undo changes in staging
    * `git reset HEAD <filename>`
  * Discard changes in workspace
    * `git checkout -- <filename>`

# maven

## check style
  * ```mvn checkstyle:checkstyle```
  * ```open target/site/checkstyle.html```

## Package
  * ```mvn package```

# tomcat

## Start/monitor logs

  * ```cd /path/to/apache-tomcat-9.0.0.M22```
  * ```rm -rf logs/*```
  * ```./bin/startup.sh```
  * ```tail -f logs/*```

## Deploy

  * Copy the war file to the webapps folder
    * ```cp target/<some>.war /path/to/apache-tomcat-9.0.0.M22/webapps/ftpxx.war```

# angular

  * To serve from the working directory
    * ng serve --open

# Angular/Augury

  * ng is the angular instance
  * ng.probe($0).componentInstance where $0 is the selected component in the Elements tab