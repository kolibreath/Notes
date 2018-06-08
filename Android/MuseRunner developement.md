# Playback
the implemetion of Playback, LocalPlayback, is able to get playback state such as IDLE BUFFERING PAUSED and NONE the states which are retrieved from the exoplayer in LocalPlayback.

## updatePlaybackState(String error);


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


# MediaSession

the MediaSession controls :`` PlaybackState``, the PlaybackState will be built up in teh playbackManager using the updastePlaybackState(String error) method whill is invoked in MusicService, the callback of QueueManager 
````
   @Override
                    public void onMetadataRetrieveError() {
                        mPlaybackManager.updatePlaybackState(
                                getString(R.string.error_no_metadata));
                    }
````

and oncreate() as for initializing the PlaybackState, which is STATE_PLAYING `cause the LocalPlayback is already running.


