from typing import Any


def is_dataclass_instance(obj: Any) -> bool:
    """
    Return True if the given object is a dataclass object, False otherwise.

    This function always returns False in py35. In py36, it returns False
    if the "dataclasses" backport is not available.

    Taken from https://docs.python.org/3/library/dataclasses.html#dataclasses.is_dataclass.
    """
    try:
        import dataclasses
    except ImportError:
        return False
    return dataclasses.is_dataclass(obj) and not isinstance(obj, type)


def is_attrs_instance(obj: Any) -> bool:
    """
    Return True if the given object is a attrs-based object, False otherwise.
    """
    try:
        import attr
    except ImportError:
        return False
    return attr.has(obj) and not isinstance(obj, type)


def is_scrapy_item(obj: Any) -> bool:
    """
    Return True if the given object is a Scrapy item, False otherwise.
    """
    try:
        import scrapy
    except ImportError:
        return False
    if isinstance(obj, scrapy.item.Item):
        return True
    try:
        # handle deprecated BaseItem
        BaseItem = getattr(scrapy.item, "_BaseItem", scrapy.item.BaseItem)
        return isinstance(obj, BaseItem)
    except AttributeError:
        return False


def is_item(obj: Any) -> bool:
    """
    Return True if the given object belongs to one of the supported types, False otherwise.
    """
    return (
        isinstance(obj, dict)
        or is_scrapy_item(obj)
        or is_dataclass_instance(obj)
        or is_attrs_instance(obj)
    )
