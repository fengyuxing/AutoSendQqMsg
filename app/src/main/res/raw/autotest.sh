#!/system/bin/sh
# Script to execute automatical test.
# By using: adb shell sendevent [device] [type] [code] [value]
#
# @author GuoLin
# Click on a point
# Samples
# Click on icon on cell 1,4
basepath=$(cd `dirname $0`; pwd);
echo basepath;
source ${basepath}/event.sh
source ${basepath}/input.sh
#click $((0x8b)) $((0x2c0))
#fling $((0x8b)) $((0x2c0)) $((0x222)) $((0x2c0))
## Drag and drop
#dad $((0x8b)) $((0x2c0)) $((0x8b)) $((0x200))
#back
#home
tap 150 1400