package gc.test.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.FightProperty;
import emu.grasscutter.net.proto.PropChangeReasonOuterClass.PropChangeReason;
import emu.grasscutter.server.packet.send.PacketAvatarFightPropUpdateNotify;
import emu.grasscutter.server.packet.send.PacketAvatarLifeStateChangeNotify;
import java.util.List;

@Command(label = "energyrecharge", usage = "/e\n\ndescription: recharge elemental energy quickly", aliases = {"e", "er", "re"}, permission = "player.setprop", permissionTargeted = "player.setprop.others")
public final class QuickERCommand implements CommandHandler {
	
	
	public void execute(Player sender, Player targetPlayer, List<String> args) {
        targetPlayer.getTeamManager().getActiveTeam().forEach(entity -> {
            boolean isAlive = entity.isAlive();
		// Add energy via EntityAvatar
            entity.addEnergy(100, PropChangeReason.PROP_CHANGE_REASON_ENERGY_BALL);
            entity.getWorld().broadcastPacket(new PacketAvatarFightPropUpdateNotify(entity.getAvatar(), FightProperty.FIGHT_PROP_CUR_HP));
            if (!isAlive) {
                entity.getWorld().broadcastPacket(new PacketAvatarLifeStateChangeNotify(entity.getAvatar()));
            }
        });
        CommandHandler.sendMessage(sender, "Recharged energy.");
    }
}
