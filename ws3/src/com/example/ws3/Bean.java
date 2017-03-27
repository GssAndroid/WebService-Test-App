package com.example.ws3;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;


public class Bean implements KvmSerializable
{
public String Mandt,Bname,Stcod,Spld,Splg,Spdb,Spda;

public Bean(){}

public Bean( String Mandt, String Bname, String Stcod, String Spld, String Splg, String Spdb, String Spda)
{
this.Mandt=Mandt;
this.Bname=Bname;
this.Stcod=Stcod;
this.Spld=Spld;
this.Splg=Splg;
this.Spdb=Spdb;
this.Spda=Spda;

}

public Object getProperty(int arg0) {
switch(arg0)
{
case 0:
return Mandt;
case 1 : return Bname;
case 2 : return Stcod;
case 3 : return Spld;
case 4 : return Splg;
case 5 : return Spdb;
case 6 : return Spda;
}
return null;
}

public int getPropertyCount() {
return 7;
}

@SuppressWarnings("rawtypes")
public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
switch(index)
{
case 0:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Mandt";
break;
case 1:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Bname";
break;
case 2:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Stcod";
break;
case 3:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Spld";
break;
case 4:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Splg";
break;
case 5:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Spdb";
break;
case 6:
info.type = PropertyInfo.STRING_CLASS;
info.name = "Spda";
break;
default:
break;
}
}

public void setProperty(int index, Object value) {
switch(index)
{
case 0:
Mandt = value.toString();
case 1 : Bname = value.toString();
case 2 : Stcod = value.toString();
case 3 : Spld = value.toString();
case 4 : Splg = value.toString();
case 5 : Spdb = value.toString();
case 6 : Spda = value.toString();

default:
break;
}
}
}


