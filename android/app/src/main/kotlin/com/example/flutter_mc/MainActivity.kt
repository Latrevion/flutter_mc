package com.example.flutter_mc
import android.os.Handler
import android.os.Looper
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.StringCodec

class MainActivity : FlutterActivity() {
    var eventSink:EventChannel.EventSink?=null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)


        //first,create a EventChannel,the name is "messageChannel",the type is String,it same as Flutter
        val channel = EventChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "messageChannel",
        )


        //second,set a  streamHandler callback ,and return 'ACK from Android'
        channel.setStreamHandler(object : EventChannel.StreamHandler{
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                 eventSink = events
                Log.d("flutter","EventChannel onListened")
                Handler(Looper.getMainLooper()).postDelayed({
                Log.d("flutter","send ACk from Android")

                    events?.success("ACK from Android")
                },1000)

            }

            override fun onCancel(arguments: Any?) {
              Log.d("flutter","EventChannel onCancel")
            }
        });
    }
}
