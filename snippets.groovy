def Plex(){
	//Start Plex
  def appcmd = "com.sony.dtv.com.plexapp.android.com.plexapp.plex.activities.SplashActivity"
  def json = "{\"method\":\"setActiveApp\",\"version\":\"1.0\",\"params\":[{\"uri\":\"${appcmd}\"}],\"id\":10}"
  def result = sendJsonAppCommand(json)
  log.debug( "hubAction = ${json}")
}


private sendJsonAppCommand(json) {
  def headers = [:]
  headers.put("HOST", "${state.tv_ip}:${tv_port}")
  headers.put("Content-Type", "application/json")
  headers.put("X-Auth-PSK", "${tv_psk}")

  def result = new physicalgraph.device.HubAction(
    method: 'POST',
    path: '/sony/appControl',
    body: json,
    headers: headers
  )

  result
}


   standardTile("Plex", "device.switch", inactiveLabel: false, height: 1, width: 1, decoration: "flat") {
      state "default", label:"Plex", action:"Plex", icon:""
    }

