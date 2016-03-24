#!/bin/bash

function display_banner (){
	echo "---------------------------------------"
	echo "Git update -  ZeroDay WebServer"
	echo "---------------------------------------"
	echo ""
}

function menu (){
	echo "[1] Mettre a jour les fichiers (action->pull)"
	echo "[2] Envoyer des fichiers (action->push)"
	echo "[3] Remplacer les changements locaux avec le dernier commit en ligne"
	echo ""
	read -p ">> Choisissez une action : "  mact
	echo ""
	if [ -z "$mact" ] || ( [ $mact -ne 1 ] && [ $mact -ne 2 ] && [ $mact -ne 3 ] )
	then
		clear
		display_banner
		menu
	fi
}

#Functions
function commit_name (){
	echo ""
	read -p ">> Entrer le nom du commit : " cmmtname
	if [ -z "$cmmtname" ]
	then
		echo ""
		echo "[-] Nom de commit invalide !"
		commit_name
	fi
}

#Main
clear
display_banner
menu
if [ "$mact" -eq "1" ]
then
	echo "[*] Telechargement des derniers fichiers ..."
	git pull > /dev/null
	echo "[+] Telechargement termine"
elif [ "$mact" -eq "2" ]
then
        echo "[*] Telechargement des derniers fichiers ..."
        git pull > /dev/null
	echo "[+] Telechargement termine"
	commit_name
	echo ""
	echo "[*] Envoie des modifications ..."
	git add ./ > /dev/null
	git commit -m "$cmmtname" > /dev/null
	git pull > /dev/null
	git push -u origin master > /dev/null
	echo "[+] Envoie termine"
	echo ""
elif [ "$mact" -eq "3" ]
then
	echo "Resetting local changes with online changes"
	git fetch origin
	git reset --hard origin/master
	echo "Reset done"
fi
exit 0
