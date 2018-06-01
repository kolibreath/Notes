# Playback
the implemetion of Playback, LocalPlayback, is able to get playback state such as IDLE BUFFERING PAUSED and NONE the states which are retrieved from the exoplayer in LocalPlayback

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

# the callbacks in Playback

PlayBack.Callback -> controls the playback state: 
````
interface Callback{
        void onCompletion();

        void onPlaybackStatusChanged(int state);

        void onError(String msg);
    }
````
Playback -> controls playback itself!
````
play()
pause()...
````

PlaybackManager implements -> Playback.Callback

MusicPlayback implements - > Playback using Exoplayer and control the playbackState.