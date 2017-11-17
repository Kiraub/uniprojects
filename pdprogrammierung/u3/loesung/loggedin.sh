##!/bin/sh

res=$(who | grep "$1")

if [ -n "$res" ] ; then
 echo "$1 is logged in"
else
 echo "$1 is not logged in"
fi
