##!/bin/sh
GLOBIGNORE='*'



while read za op zb
do
case "$op" in
'+') res=$(( za + zb ));;
'-') res=$(( za - zb ));;
'*') res=$(( za * zb ));;
'/') res=$(( za / zb ));;
*) res='Something went wrong';;
esac
echo $res
done


