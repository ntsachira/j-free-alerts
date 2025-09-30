<h1>JFreeAlerts 🚨</h1>
<p><strong>JFreeAlerts</strong> is a customizable toast-style notification system for Java Swing applications. Designed with modern UI aesthetics, sound feedback, and flexible styling, it helps developers deliver contextual alerts in desktop apps with minimal setup.</p>

<p><em>⚠️ This is the first public beta release. Notifications currently appear only from the top-right corner. Timeout customization and additional positions (bottom-right, top-center, etc.) are planned for future updates.</em></p>

<hr>

<h2>✨ Features</h2>
<ul>
  <li>🔔 Toast-style notifications from the <strong>top-right corner</strong></li>
  <li>🎨 Customizable:
    <ul>
      <li><strong>Title</strong></li>
      <li><strong>Message</strong></li>
      <li><strong>Accent color</strong></li>
      <li><strong>Icon</strong></li>
    </ul>
  </li>
  <li>🔊 Native Sound feedback</li>
  <li>🧩 GUI Builder–friendly: includes <code>JNotificationPane</code> for drag-and-drop editing</li>
</ul>

<hr>

<h2>📦 Installation</h2>
<ol>
  <li>Download the latest <code>jfreealerts.jar</code> from the <a href="https://github.com/ntsachira/j-free-alerts/releases">Releases</a> page</li>
  <li>Add it to your project’s classpath (Add to libraries as a jar)</li>
</ol>

## 🧩 GUI Builder Integration

To use `JNotificationPane` in NetBeans:

1. Go to **Tools > Palette > Swing/AWT Components > Add from JAR**
2. Select `jfreealerts-0.1-beta.jar`
3. Choose `JNotificationPane` and assign it to a category
4. Drag and drop into your UI

<hr>

<h2>🧩 Integration Guide</h2>
<ol>
  <li>Add <code>JNotificationPane</code> to your UI</li>
Use this as the root container. This component is included in the JAR and can be added via GUI builder or manually in code.
  <li>Call <code>jNotificationPane.initRuntime()</code> in your root UI class (e.g., JFrame constructor)</li>
  <li>Use <code>JAlert</code> to trigger notifications</li>
</ol>

<pre><code>
// Example usage
new JAlert(jNotificationPane.getRoot(), JAlertType.SUCCESS, "Operation completed successfully")
    .show();
</code></pre>

<pre><code>
// With customization
new JAlert(jNotificationPane.getNotificationRootPanel())
    .setAlertType(JAlertType.WARNING)
    .setTitle("Low Disk Space")
    .setMessage("Only 5% remaining")
    .setIcon(new ImageIcon("custom_icon.png"))
    .setCallBack(() -> System.out.println("Alert dismissed"))
    .show();
</code></pre>

<hr>
<h2>🧪 Demo Snippet</h2>
<pre><code>
public class DemoFrame extends JFrame {
    public DemoFrame() {
        initComponents();
        jNotificationPane.initRuntime(); // required
    }

    private void showSuccess() {
        new JAlert(jNotificationPane.getNotificationRootPanel(), JAlertType.SUCCESS, "Success message").show();
    }

    private void showError() {
        new JAlert(jNotificationPane.getNotificationRootPanel(), JAlertType.ERROR, "Error occurred").show();
    }

    private void showInfo() {
        new JAlert(jNotificationPane.getNotificationRootPanel(), JAlertType.INFO, "Information message").show();
    }

    private void showWarning() {
        new JAlert(jNotificationPane.getNotificationRootPanel(), JAlertType.WARNING, "Warning message").show();
    }
}
</code></pre>
<hr>
<h2>📚 API Referenc <code>JAlert</code></h2>

<table>
  <thead>
    <tr>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>setMessage(String)</code></td>
      <td>Sets the alert message</td>
    </tr>
    <tr>
      <td><code>setTitle(String)</code></td>
      <td>Sets the alert title</td>
    </tr>
    <tr>
      <td><code>setIcon(ImageIcon)</code></td>
      <td>Sets a custom icon</td>
    </tr>
    <tr>
      <td><code>setAlertType(JAlertType)</code></td>
      <td>Appyies predefiend styles</td>
    </tr>
    <tr>
      <td><code>setCallBack(Runnable)</code></td>
      <td>CallBack after alert is dismissed</td>
    </tr>
    <tr>
      <td><code>show()</code></td>
      <td>Displaly the alert</td>
    </tr>
    <tr>
      <td><code>getAlertType()</code></td>
      <td>Returns the current alert type</td>
    </tr>
  </tbody>
</table>
<hr>
<h2>📚 Alert Types</h2>
<ul>
  <li><code>SUCCESS</code></li>
  <li><code>ERROR</code></li>
  <li><code>INFO</code></li>
  <li><code>WARNING</code></li>
</ul>

<hr>

<h2>🧪 Demo Project</h2>
<p>See <a href="https://github.com/yourusername/JFreeAlerts-Demo">JFreeAlerts-Demo</a> for a working example.</p>

<hr>

<h2>🚧 Roadmap</h2>
Planned features for future releases:
<ul>
  <li> ⏱️ Timeout customization</li>
  <li>📍 Additional popup positions (bottom-right, top-center, etc.)</li>
  <li> 🎨 Theme presets</li>
</ul>

<hr>

<h2>📄 License</h2>
<p>MIT License (to be confirmed)</p>
