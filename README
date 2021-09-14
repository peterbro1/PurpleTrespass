# Purple Trespass

### Built for 1.12.2 spigot paper for use in purpleprison.net

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
