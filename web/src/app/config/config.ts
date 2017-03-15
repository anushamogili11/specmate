export class Config {

    public static BASE_URL = 'services/rest/';

    public static CEG_NODE_WIDTH: number = 150;
    public static CEG_NODE_HEIGHT: number = 50;

    public static CEG_MODEL_BASE_ID = 'model';
    public static CEG_NEW_MODEL_NAME = 'New Model';
    public static CEG_NEW_MODEL_DESCRIPTION = '';

    public static CEG_NODE_BASE_ID = 'node';
    public static CEG_NEW_NODE_NAME: string = 'New Node';
    public static CEG_NEW_NODE_DESCRIPTION: string = '';
    public static CEG_NEW_NODE_X: number = 100;
    public static CEG_NEW_NODE_Y: number = 100;

    public static CEG_CONNECTION_BASE_ID = 'conn';
    public static CEG_NEW_CONNECTION_NAME: string = 'New Connection';
    public static CEG_NEW_CONNECTION_DESCRIPTION: string = '';

    public static CEG_EDITOR_HEIGHT: number = 1000;

    public static CEG_EDITOR_DESCRIPTION_ROWS: number = 9;

    // The separator to separate strings from id-numbers. Must not be included in the allowed chars.
    public static ID_SEP = '-';
    public static ID_ALLOWED_CHARS = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z', '_'];
    public static ID_FORBIDDEN_REPLACEMENT = '_';
    public static ID_MIN = 1;
}