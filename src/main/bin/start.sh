#!/bin/sh
 
 
 
 
# Get home directory
APP_HOME=$(cd $(dirname $0); cd ..; pwd -P)
readonly STARTPORT=8080
readonly JAVA_HOME="/export/package/jdk1.8.0_65" # java home
readonly JAVA="$JAVA_HOME/bin/java"
OPTS_MEMORY="-Xmx2G -XX:+UseG1GC -Dfile.encoding=utf-8" #定义启动 jvm 的参数信息。
CLASSPATH="/home/export/App/conf"
JAVA_OPTS="$SGM_OPTS $JAVA_OPTS"
 
 
 
 
# Set entry
MAIN_JAR_DIR=lib
 
 
# Set log directory for log4j
LOG_DIR=/export/Logs
 
 
# Set Java environment
JAVA_ENV="-Dlog-dir=$LOG_DIR"
 
 
echo "APP_HOME="$APP_HOME
 
 
 
 
 
# CHECK IF PROC EXISTS, RETURN 0 IS OK, >0 IS EXISTS
function check_proc_if_exists {
    count=`ps -ef |grep "$1 " |grep -v "grep" |wc -l`
    return ${count}
}
 
 
# CHECK IF PROC EXISTS, RETURN 0 IS OK, >0 IS USED
function check_port_if_used {
    count=`sudo netstat -anp |grep ":$1" | grep LISTEN | wc -l`
    return ${count}
}
 
 
# CHECK IF THE DIR EXISTS, CREATE IF NOT EXISTS
function check_and_mkdir {
    if [ ! -d "$1" ]; then
        mkdir -p "$1"
    fi
}
 
 
# MAIN
function main {
    # JUMP TO APP HOME DIR, BECAUSE NOW IS IN 'bin' DIR
    cd $APP_HOME/lib
    # FIND THE JAR FILE
    local -r APP_JAR_FILE=`ls *.jar`
    echo "APP_JAR_FILE="$APP_JAR_FILE
    # CHECK IF PORT HAS BEEN USED
    check_port_if_used ${STARTPORT}
    if [[ $? -ne 0 ]]; then
        echo "$STARTPORT is used."
        exit 1
    fi
    # CHECK IF PROC HAS BEEN RUNNING
    if [ -f $APP_HOME/bin/run.pid ]; then
        local -r PID=`cat run.pid`
        check_proc_if_exists ${PID}
        if [[ $? -ne 0 ]]; then
            echo "$APP_JAR_FILE has already running. "
            exit 1
        else
            echo "run.pid is exists. will be deleted."
            $APP_HOME/bin/rm -f run.pid
        fi
    fi
      
    # CHECK STATUS OK, THEN START APP
    nohup $JAVA $OPTS_MEMORY ${JAVA_OPTS}  -jar -Xbootclasspath/a:$CLASSPATH $APP_HOME/$MAIN_JAR_DIR/$APP_JAR_FILE >/dev/null 2>&1 & echo $! > $APP_HOME/bin/run.pid
    local -r PID=$!
    echo "$APP_JAR_FILE[PID=$PID] Running at port $STARTPORT."
    echo `ls $APP_HOME/bin`
    echo `pwd`
    echo 'PID=' `cat $APP_HOME/bin/run.pid`
}

main
exit 0