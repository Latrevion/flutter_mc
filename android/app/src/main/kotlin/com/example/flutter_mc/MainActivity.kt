package com.example.flutter_mc
import android.os.Handler
import android.os.Looper
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StringCodec

class MainActivity : FlutterActivity() {

    companion object {
       const val  METHOD_GET_FLUTTER_INFO = "getFlutterInfo"
    }

    var eventSink:EventChannel.EventSink?=null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        //first,create a EventChannel,the name is "messageChannel",the type is String,it same as Flutter
        val channel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "methodChannel",
        )

        //second,register MethodChannel
        channel.setMethodCallHandler{
                methodCall: MethodCall, result:MethodChannel.Result->
            if (methodCall.method == METHOD_GET_FLUTTER_INFO){
                val name = methodCall.argument<String>("name")
                val version=methodCall.argument<String>("version")
                val language=methodCall.argument<String>("language")
                val androidApi=methodCall.argument<Int>("android_api")
                Log.d("flutter","name=$name;version=$version; language=$language;androidApi=$androidApi")
                result.success("ACK from Android")
            }
        }


    }
}
