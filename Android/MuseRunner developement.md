# PlaybackStateCompat

the Playbackstate indeed includes the speed of the playback 
the PlaybackState is built up through the PlaybackStateCompat.Builder and the user can add the speed into it.

The MusicService class implements the ServiceCallback, the PlaybackState will be passed in the MusicService using 
``onPlaybackStateUpdated(newPlaybackState)`` 

the MediaSession.setPlaybackState();


# MediaButtonReceiver

this class helps translate the  hardware playback buttons

# MediaBrowserRoot

the user can design their own pattern for returning a Browser root. As to say, the browser root is for the user to quickly find the hierarchy.