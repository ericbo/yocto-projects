SUMMARY = "Raspberry Pi squeezelite media streaming image"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-openssh"

CORE_IMAGE_EXTRA_INSTALL += "\
    squeezelite \
    alsa-utils \
    alsa-plugins \
    avahi-daemon \
    python3 \
    nano \
    curl \
    openssh-sftp-server \
"
