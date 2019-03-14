#!/bin/bash

#$1 --> devVersion

mvn versions:set -DnewVersion=$1 -DgenerateBackupPoms=false -Pjar
git commit -am $1
git push origin develop
git push --tags

