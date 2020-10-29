# Raspberry Pi
This build environment was created specifically for the origional Raspberry Pi Model B
(Broadcom BCM2835). The plan is to turn this template into one I can use for 
converting old household tech into IoT devices.

## Building

### Creating the build environment
First we need to setup out build environment. Lucky for us, the poky repo has a script
that will take care of this for us.
```bash
source poky/oe-init-build-env build
```

Assuming you don't exist your terminal, you should now be able to execute any bitbake
command. To build the image that will be use to flash the Raspberry Pi's SD card,
you can simply execute the following:
```bash
bitbake rpi-basic-image
```

**Note:** The Image can be found in the following location 
`tmp/deploy/images/raspberrypi/rpi-basic-image-raspberrypi.wic`. This image can be use
directly with `dd` or the default `Disks` application packaged with Ubuntu 20.04.
