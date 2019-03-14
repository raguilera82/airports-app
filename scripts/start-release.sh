#!/bin/bash

#$1 --> newVersion

git flow init -d -f
git flow release start $1
mvn versions:set -DnewVersion=$1 -DgenerateBackupPoms=false -Pjar
git commit -am $1
git push origin release/$1
