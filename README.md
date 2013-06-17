
JRebel support
--------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/jrebel](http://artifacts.griffon-framework.org/plugin/jrebel)


[JRebel][1] is a JVM-plugin that makes it possible for Java developers to instantly see any code change made to an app without redeploying.
JRebel lets you see code changes instantly, versioning classes and resources individually and updating one at a time instead of as a lump
application redeploy. Have a look at the [feature list][2] and see how it compares to the alternatives.

This plugin allows hot reloading of Controller, Model, Services and any other regular Groovy and Java classes. However it won't reload Views
nor any classes related to Views. You'll still have to restart the application Whenever a change is made that alters how Views are handlded
or bound to Models and Controllers.

Usage
-----

First download JRebel and obtain a license from the [JRebel site][3]. Place it under `$USER_HOME/.jrebel`.
Next define the location where **jrebel.jar** can be found in either `BuildConfig.groovy` or `$USER_HOME/.griffon/settings.groovy`, like this

        jrebel {
            location = '/Applications/ZeroTurnaround/JRebel/jrebel.jar'
        }

Start the application by typing

        $ griffon run-app

If `jrebel.location` was configured successfully and a valid license was found then you'll see the JREBEL banner appearing on the console.
Now, to trigger class reloading you must first make some changes and recompile the classes while the application is still running. Start
`griffonsh` on another console shell, this will allow for faster compilation cycles as shown in the following session.

        $ griffonsh
        Welcome to Griffon 1.0.0 - http://griffon-framework.org/
        Licensed under Apache Standard License 2.0
        Griffon home is set to: /usr/local/griffon

        Type 'exit' or ^D to terminate this interactive shell


        griffon> compile
        Resolving dependencies...
        Dependencies resolved in 602ms.
        Environment set to development
        Resolving framework plugin dependencies ...
        Framework plugin dependencies resolved in 698 ms.
        Resolving plugin dependencies ...
        Plugin dependencies resolved in 558 ms.
         [griffonc] Compiling 1 source file to /Users/aalmiray/.griffon/1.0.0/projects/sample/classes/main
        griffon> compile
        Resolving dependencies...
        Dependencies resolved in 1ms.
        Resolving framework plugin dependencies ...
        Framework plugin dependencies resolved in 586 ms.
        Resolving plugin dependencies ...
        Plugin dependencies resolved in 445 ms.
         [griffonc] Compiling 1 source file to /Users/aalmiray/.griffon/1.0.0/projects/sample/classes/main
        griffon>


Configuration
-------------

You may specify JRebel settings in either `BuildConfig.groovy` or `$USER_HOME/.griffon/settings.groovy`. These settings are passed as
System properties directly to the JRebel JVM agent. Use `jrebel.options` as a prefix. For example, setting the name and location for the
JRebel log file can be done in this way

        jrebel {
            location = '/path/to/jrebel.jar'
            options = [
                'rebel.log=true',
                'rebel.log.file=/tmp/jrebel.log'
            ]
        }

[1]: http://zeroturnaround.com/jrebel
[2]: http://zeroturnaround.com/software/jrebel/features/
[3]: http://zeroturnaround.com/software/jrebel/download/

