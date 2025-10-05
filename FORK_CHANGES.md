# Fork 配置修改摘要

本文档记录了将 `openai/openai-java` fork 为 `mrhegit/openai-java` 并配置 Maven Central 发布所做的所有修改。

## 修改日期
2025-10-05

## Maven 坐标变更

### 原坐标
- **GroupId:** `com.openai`
- **Version:** `3.5.3`
- **仓库:** `openai/openai-java`

### 新坐标
- **GroupId:** `io.github.mrhegit`
- **Version:** `3.5.3-beta.1`
- **仓库:** `mrhegit/openai-java`

## 修改文件清单（共 18 个文件）

### 1. build.gradle.kts
**修改内容：**
- ✅ `group = "io.github.mrhegit"` (原: `com.openai`)
- ✅ `version = "3.5.3-beta.1"` (原: `3.5.3`)

**修改位置：** 第 10-11 行

```kotlin
allprojects {
    group = "io.github.mrhegit"
    version = "3.5.3-beta.1" // x-release-please-version
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

### 7. .github/workflows/ci.yml (CI 优化)
**修改内容：**
- ✅ 禁用 examples job（避免需要真实 OPENAI_API_KEY）

**修改位置：** 第 86-93 行

```yaml
examples:
  timeout-minutes: 10
  name: examples
  runs-on: ${{ github.repository == 'stainless-sdks/openai-java' && 'depot-ubuntu-24.04' || 'ubuntu-latest' }}
  # Disabled for fork: requires real OPENAI_API_KEY which incurs API costs
  # To enable: add OPENAI_API_KEY to GitHub Secrets and change 'if: false' to the condition below
  # if: github.repository == 'mrhegit/openai-java' && (github.event_name == 'push' || github.event.pull_request.head.repo.fork)
  if: false
```

**说明：**
- examples job 需要真实的 OpenAI API 密钥才能运行
- 运行会产生 API 调用费用
- Fork 项目主要关注构建和发布，不需要运行真实 API 示例
- 如需启用，参考 `CI_EXAMPLES_JOB_SOLUTIONS.md` 文档

---

### 8. README.md
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

### 9. .release-please-manifest.json
**修改内容：**
- ✅ 版本号从 `3.5.3` 改为 `3.5.3-beta.1`

**修改位置：** 第 2 行

```json
{
  ".": "3.5.3-beta.1"
}
```

**说明：** 此文件用于 release-please 跟踪当前版本，必须与 `build.gradle.kts` 中的版本保持一致。

---

### 10. CONTRIBUTING.md
**修改内容：**
- ✅ 本地发布示例中的 Maven 坐标更新

**修改位置：** 第 65-72 行

#### Gradle 示例
```kotlin
implementation("io.github.mrhegit:openai-java:3.5.3-beta.1")
```

#### Maven 示例
```xml
<dependency>
  <groupId>io.github.mrhegit</groupId>
  <artifactId>openai-java</artifactId>
  <version>3.5.3-beta.1</version>
</dependency>
```

---

### 11. .github/workflows/create-releases.yml (Release Please 迁移)
**修改内容：**
- ✅ 替换 Stainless API action 为标准 release-please
- ✅ 移除 STAINLESS_API_KEY 依赖
- ✅ 调整触发条件（custom-dev 分支 + 手动触发）

**修改位置：** 第 1-26 行

**原配置（Stainless 版本）：**
```yaml
- uses: stainless-api/trigger-release-please@v1
  id: release
  with:
    repo: ${{ github.event.repository.full_name }}
    stainless-api-key: ${{ secrets.STAINLESS_API_KEY }}
```

**新配置（标准版本）：**
```yaml
- uses: actions/checkout@v4
  with:
    fetch-depth: 0  # Required for release-please to analyze commit history

- uses: googleapis/release-please-action@v4
  id: release
  with:
    token: ${{ secrets.GITHUB_TOKEN }}
```

**说明：**
- Stainless API 是官方团队使用的商业 SDK 生成平台
- Fork 项目无法获取 STAINLESS_API_KEY
- 使用标准 release-please 实现相同的自动化发布功能
- 详见 `STAINLESS_API_MIGRATION.md` 文档

---

### 12. release-please-config.json (兼容标准 release-please)
**修改内容：**
- ✅ 更改 schema 为标准 release-please
- ✅ 移除 Stainless 特有配置（prerelease、versioning）
- ✅ 调整配置结构以兼容标准版本

**修改位置：** 第 1-17 行

**关键变更：**
```json
{
  "$schema": "https://raw.githubusercontent.com/googleapis/release-please/main/schemas/config.json",
  "packages": {
    ".": {
      "release-type": "simple",
      "bump-minor-pre-major": true,
      "bump-patch-for-minor-pre-major": false,
      "extra-files": [
        "README.md",
        "build.gradle.kts"
      ]
    }
  },
  ...
}
```

**说明：**
- 移除了 `versioning: "prerelease"` 和 `prerelease: true`
- 将 `release-type` 和 `extra-files` 移到 packages 配置内
- 保留兼容的 changelog-sections 配置

---

### 13. .github/workflows/release-doctor.yml (移除 Stainless API)
**修改内容：**
- ✅ 移除 STAINLESS_API_KEY 环境变量

**修改位置：** 第 18-26 行

**说明：**
- release-doctor 工作流调用 `bin/check-release-environment` 脚本
- 移除了对 STAINLESS_API_KEY 的检查

---

### 14. bin/check-release-environment (移除 Stainless API 检查)
**修改内容：**
- ✅ 移除 STAINLESS_API_KEY 检查逻辑

**修改位置：** 第 5-7 行

**原代码：**
```bash
if [ -z "${STAINLESS_API_KEY}" ]; then
  errors+=("The STAINLESS_API_KEY secret has not been set...")
fi
```

**新代码：**
```bash
# STAINLESS_API_KEY check removed - fork project uses standard release-please
# instead of Stainless API
```

**说明：**
- 这个脚本用于发布前环境检查
- 移除了对 Stainless API 密钥的检查
- 保留了对 Sonatype 和 GPG 密钥的检查

---

### 15. .github/workflows/create-releases.yml (禁用自动 release)
**修改内容：**
- ✅ 禁用自动 release 功能
- ✅ 改为手动发布模式

**修改位置：** 第 8-14 行

**原配置：**
```yaml
jobs:
  release:
    name: release
    if: github.ref == 'refs/heads/custom-dev' && github.repository == 'mrhegit/openai-java'
```

**新配置：**
```yaml
jobs:
  release:
    name: release
    # Disabled - manual release only
    # To enable: change 'if: false' to the condition below
    # if: github.ref == 'refs/heads/custom-dev' && github.repository == 'mrhegit/openai-java'
    if: false
```

**说明：**
- 推送到 custom-dev 分支不会自动创建 Release PR
- 需要手动控制发布流程
- 详见 `MANUAL_RELEASE_GUIDE.md`

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

✅ 所有配置文件已修改完成（共 18 个文件）
✅ Maven 坐标已更新为 `io.github.mrhegit:openai-java:3.5.3-beta.1`
✅ POM 元数据已更新为 fork 信息
✅ GitHub Actions 工作流已更新仓库检查（4 个工作流）
✅ CI examples job 已禁用（避免需要真实 API 密钥）
✅ Release Please 已迁移到标准版本（移除 Stainless API 依赖）
✅ 发布环境检查脚本已移除 Stainless API 检查
✅ 自动 release 已禁用（改为手动发布模式）
✅ README 和 CONTRIBUTING 安装说明已更新
✅ release-please 版本清单已同步
✅ 新增自定义 OkHttp Dispatcher 配置功能（3 个文件）

**下一步：** 配置 GitHub Secrets 并按照 `MANUAL_RELEASE_GUIDE.md` 进行手动发布

---

## 功能增强

### 16. 支持自定义 OkHttp Dispatcher 配置

**修改日期：** 2025-10-05

**修改文件：**
- `openai-java-client-okhttp/src/main/kotlin/com/openai/client/okhttp/OkHttpClient.kt`
- `openai-java-client-okhttp/src/main/kotlin/com/openai/client/okhttp/OpenAIOkHttpClient.kt`
- `openai-java-client-okhttp/src/main/kotlin/com/openai/client/okhttp/OpenAIOkHttpClientAsync.kt`

**功能描述：**

新增了 `dispatcher()` 方法，允许用户自定义 OkHttp Dispatcher 配置，以优化并发请求性能。

**核心特性：**

1. **自定义 Dispatcher 支持**
   - 用户可以传入自定义的 `okhttp3.Dispatcher` 对象
   - SDK 会原样使用自定义 Dispatcher，不会修改其任何配置
   - 用户需要手动配置 `maxRequests` 和 `maxRequestsPerHost` 参数

2. **智能默认 Dispatcher**
   - 当用户未提供自定义 Dispatcher 时，SDK 自动创建优化的默认配置
   - 基于机器 CPU 核心数动态计算 `maxRequests` 参数
   - 计算逻辑：`maxRequests = max(64, CPU核心数 × 8)`
   - `maxRequestsPerHost` 自动与 `maxRequests` 保持一致

3. **向后兼容性**
   - 这是可选功能，不会破坏现有 API
   - 未提供自定义 Dispatcher 时，自动使用优化的默认配置
   - 现有代码无需修改即可享受性能优化

**使用示例：**

#### 使用默认优化的 Dispatcher（推荐）
```kotlin
val client = OpenAIOkHttpClient.builder()
    .apiKey("your-api-key")
    .build()

// 自动使用优化的 Dispatcher：
// - maxRequests = max(64, CPU核心数 × 8)
// - maxRequestsPerHost = maxRequests
```

#### 使用自定义 Dispatcher
```kotlin
val customDispatcher = okhttp3.Dispatcher().apply {
    maxRequests = 100
    maxRequestsPerHost = 100  // 建议与 maxRequests 保持一致
}

val client = OpenAIOkHttpClient.builder()
    .apiKey("your-api-key")
    .dispatcher(customDispatcher)
    .build()
```

#### 异步客户端使用方式
```kotlin
val client = OpenAIOkHttpClientAsync.builder()
    .apiKey("your-api-key")
    .dispatcher(customDispatcher)  // 可选
    .build()
```

**重要说明：**

⚠️ **自定义 Dispatcher 注意事项：**
- SDK 不会修改自定义 Dispatcher 的配置，完全按原样使用
- 用户需要手动配置 `maxRequests` 和 `maxRequestsPerHost`
- 建议将 `maxRequestsPerHost` 设置为与 `maxRequests` 相同（OpenAI API 通常请求同一主机）
- Dispatcher 的生命周期由 OkHttpClient 管理，调用 `close()` 时会自动关闭
- 不要在多个 OkHttpClient 实例间共享同一个 Dispatcher 对象

📊 **性能优化效果：**
- 在 8 核 CPU 机器上，默认 `maxRequests` 从 64 提升到 64（8 × 8 = 64，取最大值）
- 在 16 核 CPU 机器上，默认 `maxRequests` 从 64 提升到 128（16 × 8 = 128）
- 在 32 核 CPU 机器上，默认 `maxRequests` 从 64 提升到 256（32 × 8 = 256）
- 确保在高性能服务器上能够充分利用硬件资源

**技术实现：**

在 `OkHttpClient.Builder` 中添加了：
```kotlin
private var dispatcher: okhttp3.Dispatcher? = null

fun dispatcher(dispatcher: okhttp3.Dispatcher?) = apply {
    this.dispatcher = dispatcher
}

private fun createOptimizedDispatcher(): okhttp3.Dispatcher {
    val cpuCores = Runtime.getRuntime().availableProcessors()
    val baselineMaxRequests = 64
    val maxRequests = maxOf(baselineMaxRequests, cpuCores * 8)

    return okhttp3.Dispatcher().apply {
        this.maxRequests = maxRequests
        this.maxRequestsPerHost = maxRequests
    }
}
```

在 `OpenAIOkHttpClient.Builder` 和 `OpenAIOkHttpClientAsync.Builder` 中添加了：
```kotlin
private var dispatcher: okhttp3.Dispatcher? = null

fun dispatcher(dispatcher: okhttp3.Dispatcher?) = apply {
    this.dispatcher = dispatcher
}

fun dispatcher(dispatcher: Optional<okhttp3.Dispatcher>) =
    dispatcher(dispatcher.getOrNull())
```

**文档完善：**
- 所有 `dispatcher()` 方法都包含详细的 KDoc 注释
- 明确说明 SDK 不会修改自定义 Dispatcher 的配置
- 提供完整的使用示例和最佳实践建议
- 说明生命周期管理和使用限制

---

