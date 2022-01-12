# Purple Trespass

### Built for 1.12.2 spigot paper

### Provides a means to prevent players from accessing certain areas. I created this because the worldguard area denial takes lots of cpu time and is impractical in servers with 
### over 150 players. Also added support for removing potion effects from players in certain regions.

####

| Command | Use |
|--|--|
| help | Displays help page |
| list | Lists all blacklisted regions |
| add [region] | Adds [region] to list of blacklisted regions |
| remove [region] | Removes [region] from list of blacklisted regions |
| reload | Reloads config |

## Configuration



`Example regions.yml file:`
```yaml
#Worlds that you do not want this plugin to function in
disabled_worlds:
  - world1
  - world2
#Blacklisted regions
regions:
  - region1
  - region2
#Add regions here with lists of potion effects that should be removed from players that enter.
#Regions do not have to be balcklisted to be listed here.
region1:
- POISON
- WITHER

region2:
- REGENERATION
- POISON

region3:
- CONFUSION
- SLOWNESS
- REGENERATION

```
`Example Settings.yml file:`
```yaml
#How often should the plugin check for players in regions
CHECK_INTERVAL: '5'
#The command to issue to the player if found in the regions
PUNISHMENT_COMMAND: spawn %player%
```


This is now abandoned, and I do not plan to maintain it. If you'd like to become the maintainer, send me a DM
