#!/bin/bash

#DO NOT REMOVE THE FOLLOWING TWO LINES
git add $0 >> .local.git.out
git commit -a -m "Lab 2 commit" >> .local.git.out
git push >> .local.git.out || echo


#Your code here
file="$1"
while IFS= read -r password
do
	length=${#password}
	six=6
	thrt=32
	if [[ "$length" -gt "$thrt" ]];then
		echo "Error: Password length invalid."
		continue
	fi
	if [[ "$length" -lt "$six" ]]; then
		echo "Error: Password length invalid."
		continue
	fi
	score=$length
	if [[ $password == *[#$+%@]* ]];then
		((score = score + 5))
		#echo "CHECK"
	fi
	if [[ $password == *[0-9]* ]];then
		((score = score + 5))
		#echo "THIS"
	fi
	if [[ $password == *[A-Za-z]* ]];then
		((score = score + 5))
		#echo "HERE"
	fi
	line='([a-zA-Z0-9])\1+'
	if [[ $password =~ $line ]];then
		((score = score - 10))
		#echo "nice"
	fi
	if [[ $password =~ [a-z][a-z][a-z] ]];then
		((score = score - 3))
		#echo sweet
	fi
	if [[ $password =~ [A-Z][A-Z][A-Z] ]];then
		((score = score - 3))
		#echo lets get it
	fi
	if [[ $password =~ [0-9][0-9][0-9] ]];then
		((score = score - 3))
		#echo yeah
	fi
	echo "Password Score: $score"
done < "$file"
