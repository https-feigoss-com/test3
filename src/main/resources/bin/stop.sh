#!/bin/bash
 
  
APP_HOME=$(cd $(dirname $0); cd ..; pwd -P)
# CHECK IF PROC EXISTS, RETURN 0 IS OK, >0 IS EXISTS
function check_proc_if_exists {
 
   
   
   
  count=`ps -ef |grep "$1 " |grep -v "grep" |wc -l`
 
   
   
   
  echo "$1"
 
   
   
   
  return ${count}
}
 
   
  
function stop
{
 
   
   
   
  timeout=20
 
   
   
   
  interval=1
 
   
   
   
  if [ -f $APP_HOME/bin/run.pid ]; then
 
   
   
   
   
   
   
   
  PID=`cat $APP_HOME/bin/run.pid`
 
   
   
   
   
   
   
   
   
  
 
   
   
   
   
   
   
   
  echo 'PID  =======  $PID'
 
   
   
   
   
   
   
   
   
  
 
   
   
   
   
   
   
   
  check_proc_if_exists ${PID}
 
   
   
   
   
   
   
   
  if [[ $? -eq 0 ]]; then
 
   
   
   
   
   
   
   
   
   
   
   
  /bin/rm -f run.pid
 
   
   
   
   
   
   
   
   
   
   
   
  echo "WARNING: process not found, nothing to stop" >&2
 
   
   
   
   
   
   
   
   
   
   
   
  exit 0
 
   
   
   
   
   
   
   
  fi
 
   
   
   
  else
 
   
   
   
   
   
   
   
  echo "WARNING: process not found, nothing to stop" >&2
 
   
   
   
   
   
   
   
  exit 0
 
   
   
   
  fi
 
   
   
   
  echo "process[$PID] is running. will be stopped."
 
   
   
   
  kill ${PID}
 
   
   
   
  check_proc_if_exists ${PID}
 
   
   
   
  while (( timeout > 0 )) && (( $? > 0 )); do
 
   
   
   
   
   
   
   
  echo -n "."?
 
   
   
   
   
   
   
   
  sleep ${interval}
 
   
   
   
   
   
   
   
  timeout=$(( timeout - interval ))
 
   
   
   
   
   
   
   
  check_proc_if_exists ${PID}
 
   
   
   
  done
 
   
   
   
  check_proc_if_exists ${PID}
 
   
   
   
  if [[ $? -ne 0 ]]; then
 
   
   
   
   
   
   
   
  echo "WARNING: process still alive, sending SIGKILL ..." >&2
 
   
   
   
   
   
   
   
  kill -9 ${PID}
 
   
   
   
  fi
}
 
   
  
echo "11111"
cd ..
stop
echo "STOP FINISHIED"
echo "">/export/Logs/creditAsync.jintiao.jd.local/tmp.log