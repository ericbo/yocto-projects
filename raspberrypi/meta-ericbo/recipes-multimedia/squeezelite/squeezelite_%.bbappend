FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://squeezelite.service \
    file://squeezelite.default \
"

inherit systemd

SYSTEMD_SERVICE_${PN} = "squeezelite.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/squeezelite.service ${D}${systemd_unitdir}/system/squeezelite.service

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/squeezelite.default ${D}${sysconfdir}/default/squeezelite
}

FILES_${PN} += " \
    ${systemd_unitdir}/system/squeezelite.service \
    ${sysconfdir}/default/squeezelite \
"
