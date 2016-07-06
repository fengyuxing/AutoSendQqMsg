#!/system/bin/sh
# Script to execute automatical test
# By using: adb shell sendevent [device] [type] [code] [value]
# @author GuoLin
# Click on a point
# Move from a point to another
EVENT=/dev/input/event8
move()
{
    for i in 1 2 3 4
    do
        x=$(( ($3 - $1) * $i / 3 + $1 ))
        y=$(( ($4 - $2) * $i / 3 + $2 ))
        press ${x} ${y}
    done;
}

# Press a point
press()
{
    sendevent $EVENT 3 $((0x35)) $1
    sendevent $EVENT 3 $((0x36)) $2
    sendevent $EVENT 3 $((0x30)) $((0x44))
    sendevent $EVENT 3 $((0x32)) $((0x04))
    sendevent $EVENT 3 $((0x39)) $((0x00))
    sendevent $EVENT 0 $((0x02)) $((0x00))
    sendevent $EVENT 0 $((0x00)) $((0x00))
}

# Release on a point
release()
{
    sendevent $EVENT 3 $((0x35)) $1
    sendevent $EVENT 3 $((0x36)) $2
    sendevent $EVENT 3 $((0x30)) $((0x00))
    sendevent $EVENT 3 $((0x32)) $((0x04))
    sendevent $EVENT 3 $((0x39)) $((0x00))
    sendevent $EVENT 0 $((0x02)) $((0x00))
    sendevent $EVENT 0 $((0x00)) $((0x00))
}

click()
{
    press $1 $2
    release $1 $2
}

# Long click on a point
longclick()
{
    press $1 $2
    press $1 $2
    sleep 1
}

# Fling from a point to another
fling()
{
    move $1 $2 $3 $4
    release $3 $4
}

# Drag and drop
dad()
{
    longclick $1 $2
    move $1 $2 $3 $4
    release $3 $4
}

back()
{
    input keyevent 4
}

home()
{
    input keyevent 3
}