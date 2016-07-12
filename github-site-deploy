#!/usr/bin/env sh

set -eu

# Generate site and upload to github.
#
# Need server like this in ~/.m2/settings.xml
#  <servers>
#    <server>
#      <id>github</id>
#      <password>oauth2-token-with-public_repo-and-user:email</password>
#    </server>
#  </servers>
./mvnw -B -e -Dgithub.global.server=github clean site-deploy
