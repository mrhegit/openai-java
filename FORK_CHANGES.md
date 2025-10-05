# Fork 配置修改摘要

本文档记录了将 `openai/openai-java` fork 为 `mrhegit/openai-java` 并配置 Maven Central 发布所做的所有修改。

## 修改日期
2025-01-15

## Maven 坐标变更

### 原坐标
- **GroupId:** `com.openai`
- **Version:** `3.5.3`
- **仓库:** `openai/openai-java`

### 新坐标
- **GroupId:** `io.github.mrhegit`
- **Version:** `1.0.0`
- **仓库:** `mrhegit/openai-java`

## 修改文件清单（共 9 个文件）

### 1. build.gradle.kts
**修改内容：**
- ✅ `group = "io.github.mrhegit"` (原: `com.openai`)
- ✅ `version = "1.0.0"` (原: `3.5.3`)

**修改位置：** 第 10-11 行

```kotlin
allprojects {
    group = "io.github.mrhegit"
    version = "1.0.0" // x-release-please-version
}
```

---

### 2. buildSrc/src/main/kotlin/openai.publish.gradle.kts
**修改内容：**
- ✅ POM 项目名称添加 Fork 标识
- ✅ POM 描述说明这是 fork 版本
- ✅ 项目 URL 更新为 `https://github.com/mrhegit/openai-java`
- ✅ 开发者信息更新：
  - `id.set("mrhegit")`
  - `name.set("mrhegit")`
  - `email.set("drivemrhe@gmail.com")`
  - `url.set("https://github.com/mrhegit")`
- ✅ SCM 信息更新为 `mrhegit/openai-java`
- ✅ 许可证 URL 添加完整路径

**修改位置：** 第 31-57 行

```kotlin
pom {
    name.set("OpenAI Java SDK (Fork by mrhegit)")
    description.set("Forked version of the OpenAI Java SDK with custom enhancements. Based on official openai-java v3.5.3. Please see https://platform.openai.com/docs/api-reference for API details.")
    url.set("https://github.com/mrhegit/openai-java")

    licenses {
        license {
            name.set("Apache-2.0")
            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }

    developers {
        developer {
            id.set("mrhegit")
            name.set("mrhegit")
            email.set("drivemrhe@gmail.com")
            url.set("https://github.com/mrhegit")
        }
    }

    scm {
        connection.set("scm:git:git://github.com/mrhegit/openai-java.git")
        developerConnection.set("scm:git:ssh://git@github.com/mrhegit/openai-java.git")
        url.set("https://github.com/mrhegit/openai-java")
    }
}
```

---

### 3. .github/workflows/create-releases.yml
**修改内容：**
- ✅ 仓库检查条件从 `openai/openai-java` 改为 `mrhegit/openai-java`

**修改位置：** 第 12 行

```yaml
if: github.ref == 'refs/heads/main' && github.repository == 'mrhegit/openai-java'
```

---

### 4. .github/workflows/release-doctor.yml
**修改内容：**
- ✅ 仓库检查条件从 `openai/openai-java` 改为 `mrhegit/openai-java`

**修改位置：** 第 13 行

```yaml
if: github.repository == 'mrhegit/openai-java' && (github.event_name == 'push' || github.event_name == 'workflow_dispatch' || startsWith(github.head_ref, 'release-please') || github.head_ref == 'next')
```

---

### 5. .github/workflows/ci.yml
**修改内容：**
- ✅ examples job 的仓库检查条件从 `openai/openai-java` 改为 `mrhegit/openai-java`

**修改位置：** 第 90 行

```yaml
if: github.repository == 'mrhegit/openai-java' && (github.event_name == 'push' || github.event.pull_request.head.repo.fork)
```

---

### 6. .github/workflows/detect-breaking-changes.yml
**修改内容：**
- ✅ 仓库检查条件从 `openai/openai-java` 改为 `mrhegit/openai-java`

**修改位置：** 第 12 行

```yaml
if: github.repository == 'mrhegit/openai-java'
```

---

### 7. README.md
**修改内容：**
- ✅ 标题添加 Fork 标识
- ✅ 添加 Fork 说明 NOTE 块
- ✅ Maven Central 徽章更新为 `io.github.mrhegit:openai-java:1.0.0`
- ✅ Javadoc 徽章更新为 `io.github.mrhegit:openai-java:1.0.0`
- ✅ 安装说明中的 Gradle 依赖更新
- ✅ 安装说明中的 Maven 依赖更新
- ✅ Spring Boot Starter 的 Gradle 依赖更新
- ✅ Spring Boot Starter 的 Maven 依赖更新

**主要修改位置：**

#### 标题和徽章（第 1-7 行）
```markdown
# OpenAI Java API Library (Fork by mrhegit)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.mrhegit/openai-java)](https://central.sonatype.com/artifact/io.github.mrhegit/openai-java/1.0.0)
[![javadoc](https://javadoc.io/badge2/io.github.mrhegit/openai-java/1.0.0/javadoc.svg)](https://javadoc.io/doc/io.github.mrhegit/openai-java/1.0.0)
```

#### Fork 说明（第 10-17 行）
```markdown
> [!NOTE]
> **This is a forked version** of the official OpenAI Java SDK with custom enhancements.
> 
> - **Official SDK:** [`com.openai:openai-java`](https://github.com/openai/openai-java)
> - **This Fork:** `io.github.mrhegit:openai-java`
> - **Based on version:** 3.5.3
> 
> For the official SDK, visit: https://github.com/openai/openai-java
```

#### Gradle 依赖（第 36 行）
```kotlin
implementation("io.github.mrhegit:openai-java:1.0.0")
```

#### Maven 依赖（第 43-45 行）
```xml
<groupId>io.github.mrhegit</groupId>
<artifactId>openai-java</artifactId>
<version>1.0.0</version>
```

#### Spring Boot Starter Gradle（第 1354 行）
```kotlin
implementation("io.github.mrhegit:openai-java-spring-boot-starter:1.0.0")
```

#### Spring Boot Starter Maven（第 1361-1363 行）
```xml
<groupId>io.github.mrhegit</groupId>
<artifactId>openai-java-spring-boot-starter</artifactId>
<version>1.0.0</version>
```

---

### 8. .release-please-manifest.json
**修改内容：**
- ✅ 版本号从 `3.5.3` 改为 `1.0.0`

**修改位置：** 第 2 行

```json
{
  ".": "1.0.0"
}
```

**说明：** 此文件用于 release-please 跟踪当前版本，必须与 `build.gradle.kts` 中的版本保持一致。

---

### 9. CONTRIBUTING.md
**修改内容：**
- ✅ 本地发布示例中的 Maven 坐标更新

**修改位置：** 第 65-72 行

#### Gradle 示例
```kotlin
implementation("io.github.mrhegit:openai-java:1.0.0")
```

#### Maven 示例
```xml
<dependency>
  <groupId>io.github.mrhegit</groupId>
  <artifactId>openai-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

---

## 发布前检查清单

### Sonatype 配置
- [ ] 已在 Sonatype Central Portal 注册账号
- [ ] 已验证 `io.github.mrhegit` 命名空间
- [ ] 命名空间状态为 **Verified** ✅

### GitHub Secrets 配置
- [ ] `SONATYPE_USERNAME` - Sonatype 用户名
- [ ] `SONATYPE_PASSWORD` - Sonatype 密码或 Token
- [ ] `GPG_SIGNING_KEY` - GPG 私钥完整内容
- [ ] `GPG_SIGNING_PASSWORD` - GPG 密钥密码

### GPG 密钥配置
- [ ] 已生成 GPG 密钥对（4096 位 RSA）
- [ ] 密钥邮箱为 `drivemrhe@gmail.com`
- [ ] 公钥已上传到密钥服务器（keyserver.ubuntu.com）
- [ ] 私钥已导出并配置到 GitHub Secrets

### 本地测试
- [ ] 执行 `./gradlew publishToMavenLocal` 成功
- [ ] 验证生成的 POM 文件内容正确
- [ ] 验证 GPG 签名文件存在且有效
- [ ] 所有测试通过 `./gradlew test`

### 代码提交
- [ ] 所有修改已提交到 main 分支
- [ ] 创建版本标签 `v1.0.0`（可选）

---

## 发布流程

### 方法 1：手动触发 GitHub Actions
1. 访问：https://github.com/mrhegit/openai-java/actions/workflows/publish-sonatype.yml
2. 点击 **Run workflow**
3. 选择分支：`main`
4. 点击 **Run workflow** 确认

### 方法 2：本地命令行发布
```bash
# 设置环境变量
export GPG_SIGNING_KEY="$(cat private-key.asc)"
export GPG_SIGNING_PASSWORD="your-gpg-password"

# 执行发布
./gradlew publishAndReleaseToMavenCentral \
  -PmavenCentralUsername="your-sonatype-username" \
  -PmavenCentralPassword="your-sonatype-password" \
  --no-configuration-cache \
  --stacktrace
```

---

## 验证发布成功

### 1. 检查 Sonatype Central Portal
- 登录：https://central.sonatype.com/
- 查看 Deployments 状态
- 确认状态为 **PUBLISHED** ✅

### 2. 验证 Maven Central 同步
```bash
# 等待 15-30 分钟后执行
curl -I https://repo1.maven.org/maven2/io/github/mrhegit/openai-java/1.0.0/openai-java-1.0.0.pom

# 预期输出：HTTP/1.1 200 OK
```

### 3. 测试依赖下载
创建测试项目并添加依赖：
```kotlin
implementation("io.github.mrhegit:openai-java:1.0.0")
```

执行 `./gradlew dependencies` 验证依赖解析成功。

---

## 后续维护

### 版本更新
1. 修改 `build.gradle.kts` 中的 `version`
2. 更新 `CHANGELOG.md`
3. 提交并打标签
4. 触发发布工作流

### 同步官方更新
```bash
git remote add upstream https://github.com/openai/openai-java.git
git fetch upstream
git merge upstream/main
# 解决冲突，保留自定义配置
git push origin main
```

---

## 注意事项

1. **包名未修改**：Java 包名仍为 `com.openai.*`，不能与官方包共存
2. **版本独立**：使用独立版本序列 `1.0.0`，避免与官方版本混淆
3. **许可证保留**：保持 Apache-2.0 许可证不变
4. **Fork 标识**：在所有用户可见的地方明确标注这是 fork 版本

---

## 相关链接

- **Fork 仓库：** https://github.com/mrhegit/openai-java
- **官方仓库：** https://github.com/openai/openai-java
- **Maven Central：** https://central.sonatype.com/artifact/io.github.mrhegit/openai-java
- **Javadoc：** https://javadoc.io/doc/io.github.mrhegit/openai-java
- **Sonatype Central Portal：** https://central.sonatype.com/

---

## 修改完成确认

✅ 所有配置文件已修改完成（共 9 个文件）
✅ Maven 坐标已更新为 `io.github.mrhegit:openai-java:1.0.0`
✅ POM 元数据已更新为 fork 信息
✅ GitHub Actions 工作流已更新仓库检查（4 个工作流）
✅ README 和 CONTRIBUTING 安装说明已更新
✅ release-please 版本清单已同步

**下一步：** 配置 GitHub Secrets 并执行首次发布

