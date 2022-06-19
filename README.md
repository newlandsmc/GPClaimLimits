# GPClaimLimits

___
Plugin designed for [SemiVanilla-MC](https://github.com/SemiVanilla-MC/SemiVanilla-MC).

## **Downloads**
Downloads can be obtained on the [github actions page.](https://github.com/SemiVanilla-MC/GPClaimLimits/actions)

## **Building**

#### Initial setup
Clone the repo using `git clone https://github.com/SemiVanilla-MC/GPClaimLimits.git`.

#### Compiling
Use the command `./gradlew build --stacktrace` in the project root directory.
The compiled jar will be placed in directory `/build/libs/`.

## **Commands**

| Command     | Description                             | Permission          |
|-------------|-----------------------------------------|---------------------|

## **Permissions**

| Permission                    | Description          |
|-------------------------------|----------------------|
| semivanilla.claimlimit.bypass | Bypass claim limits. |

## **Configuration**

```yaml
# Magic value used to determine auto configuration updates, do not change this value
config-version: 2

messages:
  claim-limit-reached: <red>You have reached your current claim limit.
  claim-above-limit: <red>You can not create this claim as you are already at your limit.
```

## **Support**

## **License**
[LICENSE](LICENSE)