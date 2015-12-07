/*************************************************************************
	> File Name: hdysys.c
	> Author: 韦启发
	> Mail: 329410527@qq.com
	> Created Time: 2015年12月07日 星期一 17时04分32秒
 ************************************************************************/
#include <linux/module.h>
#include <linux/init.h>
#include <linux/device.h>
#include <linux/string.h>
#include <linux/sysfs.h>
#include <linux/kernel.h>

MODULE_AUTHOR("weiqifa");
MODULE_LICENSE("Dual BSD/GPL");

void my_obj_release(struct kobject *kobj)
{
printk("release ok.n");
}

ssize_t my_sysfs_show(struct kobject *kobj, struct attribute *attr, char *buf)
{
printk("my_sysfs_show.n");
printk("attrname:%s.n", attr->name);
sprintf(buf, "%s", attr->name);
return strlen(attr->name) + 1;
}

ssize_t my_sysfs_store(struct kobject *kobj, struct attribute *attr, const char *buf,
   size_t count)
{
printk("my_sysfs_store.n");
printk("write:%sn", buf);

return count;
}

struct sysfs_ops my_sysfs_ops = {
.show = my_sysfs_show,
.store = my_sysfs_store,
};

struct attribute my_attrs = {
.name = "wd",
.mode = S_IRWXUGO,
};

struct attribute *my_attrs_def[] = {
&my_attrs,
NULL,
};
struct kobj_type my_ktype = {
.release = my_obj_release,
.sysfs_ops = &my_sysfs_ops,
.default_attrs = my_attrs_def,
};

struct kobject my_kobj ;

int __init kobj_test_init(void)
{
printk("kobj_test init.n");
kobject_init_and_add(&my_kobj, &my_ktype, NULL, "weiqifa");

return 0;
}

void __exit kobj_test_exit(void)
{
printk("kobj_test exit.n");
kobject_del(&my_kobj);
}

module_init(kobj_test_init);
module_exit(kobj_test_exit);


