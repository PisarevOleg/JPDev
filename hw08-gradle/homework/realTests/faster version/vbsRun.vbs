countRepeats=5
reportFileName="report.csv"
saveString="Timing,Xms,Xmx,GCollector"
call saveFile(reportFileName,saveString)

Xms=256
Xmx=256

additionStep=128
countSteps=14

for i3=0 to 4
 if i3=0 then icg="UseG1GC"
 if i3=1 then icg="UseSerialGC"
 if i3=2 then icg="UseParallelGC"
 if i3=3 then icg="UseConcMarkSweepGC"
 if i3=4 then icg="UseZGC"
 
 for i1=0 to countSteps-1
  iXms=Xms+additionStep*i1
  
  for i2=0 to countSteps-1
   iXmx=Xmx+additionStep*i2
   
   summTiming=0
   for i4=1 to countRepeats
    
    Set oShell = WScript.CreateObject("WScript.Shell")
    t1 = Timer
    'Wscript.echo "Начало выполнения: " & showTime(t1)
    oShell.run "java -jar -Xms" & iXms & "m -Xmx" & iXmx & "m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/heapdump.hprof -XX:+" & icg & " -Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m all-in-one-jar.jar", 0, true
    t2 = Timer
    'Wscript.echo "Окончание выполнения: " & showTime(t2) 
    summTiming=summTiming+(t2-t1)
   next
   
   saveString=replace(summTiming/countRepeats,",",".") & "," & iXms & "," & iXmx & "," & icg
   call saveFile(reportFileName,saveString)
   
  next 
 next
next

function saveFile(savedFileName, saveString)
 Set FSO = CreateObject("Scripting.FileSystemObject")
 Set f = FSO.OpenTextFile(savedFileName, 8, True)
 f.WriteLine(saveString)
 f.Close
end function

function showTime(t)
 temp = Int(t)

 Milliseconds = Int((t-temp) * 1000)

 Seconds = temp mod 60
 temp    = Int(temp/60)
 Minutes = temp mod 60
 Hours   = Int(temp/60)

 strTime =           String(2 - Len(Hours), "0") & Hours & ":"
 strTime = strTime & String(2 - Len(Minutes), "0") & Minutes & ":"
 strTime = strTime & String(2 - Len(Seconds), "0") & Seconds & "."
 strTime = strTime & String(4 - Len(Milliseconds), "0") & Milliseconds

 showTime = strTime
end function 