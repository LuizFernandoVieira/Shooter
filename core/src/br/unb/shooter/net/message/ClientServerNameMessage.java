package br.unb.shooter.net.message;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.util.Constants;

public class ClientServerNameMessage extends Message {

    private String clientIp;

    private List<String> ips;

    private Integer ipsLength;

    public ClientServerNameMessage() {
        this.id = MessageEnum.CLIENT_SERVER_NAME.getId();
    }

    /**
     * Constructor.
     * 
     * @param ips IP's list
     */
    public ClientServerNameMessage(List<InetAddress> ips, String clientIp) {
        this.id = MessageEnum.CLIENT_SERVER_NAME.getId();

        this.clientIp = clientIp;

        this.ipsLength = ips.size();

        this.ips = new ArrayList<String>();

        for (InetAddress ip : ips) {
            this.ips.add(ip.getHostAddress());
        }
    }

    public ClientServerNameMessage(String message) {
        this.id = MessageEnum.CLIENT_SERVER_NAME.getId();
        translate(message);
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = slices[0];

        this.clientIp = slices[1];

        this.ipsLength = Integer.valueOf(slices[2]);

        this.ips = new ArrayList<String>();

        for (int i = 0; i < this.ipsLength; i++) {
            this.ips.add(slices[i + 3]);
        }
    }

    @Override
    public void execute() {
        for (String ip : ips) {
            if (NetController.getInstance().isServerIp(ip)) {
                NetController.getInstance().tellClientServerName(clientIp);
            }
        }
    }

    @Override
    public String toString() {
        String msg = this.id + Constants.SPACE + this.clientIp + Constants.SPACE + this.ipsLength;

        for (String ip : this.ips) {
            msg += (Constants.SPACE + ip);
        }

        return msg;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public Integer getIpsLength() {
        return ipsLength;
    }

    public void setIpsLength(Integer ipsLength) {
        this.ipsLength = ipsLength;
    }

}
