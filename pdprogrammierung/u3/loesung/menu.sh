##!/bin/bash

menu1(){
echo "$LOGNAME 1 $(date)" >> logfile.txt
echo "User name >"
read username
sh loggedin.sh $username
}

menu2(){
echo "$LOGNAME 2 $(date)" >> logfile.txt
echo "File size threshold >"
read s
find * -size +$s
}





userinput=1

while [ "$userinput" -le 4 -a "$userinput" -ge 1 ] ;do

echo "[1] Find user
[2] List large files
[3] Disk usage
[4] View Log File
[5] Exit

Your choice >"

read userinput

case "$userinput" in
1) menu1
;;
2) menu2
;;
3) echo "$LOGNAME 3 $(date)" >> logfile.txt
du
;;
4)
cat logfile.txt
;;
5) ;;
*)echo "Wrong number";;
esac


done




