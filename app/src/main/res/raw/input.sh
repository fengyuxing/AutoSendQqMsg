#!/system/bin/sh
# Script to execute automatical test
# By using: adb shell input [<source>] <command> [<arg>...]
# @author GuoLin
# Click on a point
# Move from a point to another

back()
{
    input keyevent 4
}

home()
{
    input keyevent 3
}

#click
tap()
{
    input tap $1 $2
}

#swipe
swipe()
{
    input swipe $1 $2 $3 $4
}